package tetris.game;

import java.awt.Color;

import tetris.shapes.*;

public class GameEngine
{
	public static final int ROWS = 20;
	public static final int COLS = 10;
	private static final int LOCK_DELAY_MS = 500;
	private static final int[] LINE_SCORES = {0, 100, 300, 500, 800};

	private final Color[][] board = new Color[ROWS][COLS];
	private final Shape[] prototypes;
	private final BagRandomizer bag = new BagRandomizer();

	private Shape current;
	private int nextType;
	private Integer heldType;
	private boolean holdUsed;

	private GameState state = GameState.READY;
	private int score;
	private int lines;
	private int level = 1;
	private int lockTimerMs;
	private boolean onGround;

	public GameEngine()
	{
		prototypes = new Shape[] {
			new IShape(),
			new JShape(),
			new LShape(),
			new OShape(),
			new SShape(),
			new TShape(),
			new ZShape()
		};
		nextType = bag.next();
	}

	public void start()
	{
		clearBoard();
		score = 0;
		lines = 0;
		level = 1;
		heldType = null;
		holdUsed = false;
		lockTimerMs = 0;
		onGround = false;
		nextType = bag.next();
		state = GameState.PLAYING;
		spawnPiece();
	}

	public void restart()
	{
		start();
	}

	public GameState getState()
	{
		return state;
	}

	public int getScore()
	{
		return score;
	}

	public int getLines()
	{
		return lines;
	}

	public int getLevel()
	{
		return level;
	}

	public int getGravityIntervalMs()
	{
		return Math.max(100, 1000 - (level - 1) * 80);
	}

	public Color[][] getBoard()
	{
		return board;
	}

	public Shape getCurrent()
	{
		return current;
	}

	public int getNextType()
	{
		return nextType;
	}

	public Integer getHeldType()
	{
		return heldType;
	}

	public Shape getPrototype(int type)
	{
		return prototypes[type];
	}

	public int getGhostY()
	{
		if (current == null) {
			return 0;
		}

		int ghostY = current.getY();
		while (isValidAt(current, current.getX(), ghostY + 1, current.getCurrentRotation())) {
			ghostY++;
		}
		return ghostY;
	}

	public void togglePause()
	{
		if (state == GameState.PLAYING) {
			state = GameState.PAUSED;
		} else if (state == GameState.PAUSED) {
			state = GameState.PLAYING;
		}
	}

	public void tick()
	{
		if (state != GameState.PLAYING || current == null) {
			return;
		}

		if (!tryMoveDown()) {
			if (!onGround) {
				onGround = true;
				lockTimerMs = LOCK_DELAY_MS;
			} else {
				lockTimerMs -= getGravityIntervalMs();
				if (lockTimerMs <= 0) {
					lockCurrentPiece();
				}
			}
		}
	}

	public void moveLeft()
	{
		tryMove(current.getX() - 1, current.getY(), current.getCurrentRotation());
	}

	public void moveRight()
	{
		tryMove(current.getX() + 1, current.getY(), current.getCurrentRotation());
	}

	public boolean softDrop()
	{
		if (tryMoveDown()) {
			score += level;
			resetLockIfOnGround();
			return true;
		}
		return false;
	}

	public void hardDrop()
	{
		if (state != GameState.PLAYING || current == null) {
			return;
		}

		int distance = 0;
		while (tryMoveDown()) {
			distance++;
		}
		score += distance * 2 * level;
		lockCurrentPiece();
	}

	public void rotateClockwise()
	{
		tryRotate(1);
	}

	public void rotateCounterClockwise()
	{
		tryRotate(-1);
	}

	public void hold()
	{
		if (state != GameState.PLAYING || current == null || holdUsed) {
			return;
		}

		int currentType = current.getTypeIndex();
		holdUsed = true;
		onGround = false;
		lockTimerMs = 0;

		if (heldType == null) {
			heldType = currentType;
			spawnPiece();
		} else {
			int swap = heldType;
			heldType = currentType;
			spawnFromType(swap);
		}
	}

	private void spawnPiece()
	{
		spawnFromType(nextType);
		nextType = bag.next();
		holdUsed = false;
		onGround = false;
		lockTimerMs = 0;
	}

	private void spawnFromType(int type)
	{
		current = prototypes[type].createCopy();
		current.spawn(COLS);

		if (!isValidPosition(current)) {
			state = GameState.GAME_OVER;
			current = null;
		}
	}

	private void lockCurrentPiece()
	{
		if (current == null) {
			return;
		}

		placeOnBoard(current);
		current = null;
		onGround = false;
		lockTimerMs = 0;

		int cleared = clearLines();
		if (cleared > 0) {
			score += LINE_SCORES[cleared] * level;
			lines += cleared;
			level = lines / 10 + 1;
		}

		spawnPiece();
	}

	private void placeOnBoard(Shape shape)
	{
		int[][] matrix = shape.getShape();
		int h = shape.getHeight();
		int w = shape.getWidth();
		Color color = shape.getColor();
		int xPos = shape.getX();
		int yPos = shape.getY();

		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (matrix[r][c] == 1) {
					int y = r + yPos;
					int x = c + xPos;
					if (y >= 0 && y < ROWS && x >= 0 && x < COLS) {
						board[y][x] = color;
					}
				}
			}
		}
	}

	private int clearLines()
	{
		int cleared = 0;

		for (int r = ROWS - 1; r >= 0; r--) {
			if (isLineFull(r)) {
				removeLine(r);
				cleared++;
				r++;
			}
		}

		return cleared;
	}

	private boolean isLineFull(int row)
	{
		for (int c = 0; c < COLS; c++) {
			if (board[row][c] == null) {
				return false;
			}
		}
		return true;
	}

	private void removeLine(int row)
	{
		for (int r = row; r > 0; r--) {
			System.arraycopy(board[r - 1], 0, board[r], 0, COLS);
		}
		for (int c = 0; c < COLS; c++) {
			board[0][c] = null;
		}
	}

	private boolean tryMoveDown()
	{
		return tryMove(current.getX(), current.getY() + 1, current.getCurrentRotation());
	}

	private boolean tryMove(int x, int y, int rotation)
	{
		if (state != GameState.PLAYING || current == null) {
			return false;
		}

		if (isValidAt(current, x, y, rotation)) {
			current.setX(x);
			current.setY(y);
			current.setRotation(rotation);
			resetLockIfOnGround();
			return true;
		}
		return false;
	}

	private void tryRotate(int direction)
	{
		if (state != GameState.PLAYING || current == null) {
			return;
		}

		int oldRotation = current.getCurrentRotation();
		int newRotation = (oldRotation + direction + 4) % 4;
		int oldX = current.getX();
		int oldY = current.getY();

		int[] kicks = {0, -1, 1, -2, 2};
		for (int kick : kicks) {
			if (isValidAt(current, oldX + kick, oldY, newRotation)) {
				current.setX(oldX + kick);
				current.setY(oldY);
				current.setRotation(newRotation);
				resetLockIfOnGround();
				return;
			}
		}
	}

	private void resetLockIfOnGround()
	{
		if (onGround) {
			lockTimerMs = LOCK_DELAY_MS;
		}
	}

	private boolean isValidPosition(Shape shape)
	{
		return isValidAt(shape, shape.getX(), shape.getY(), shape.getCurrentRotation());
	}

	private boolean isValidAt(Shape shape, int x, int y, int rotation)
	{
		int[][] matrix = shape.getShapeAtRotation(rotation);
		int h = matrix.length;
		int w = matrix[0].length;

		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (matrix[r][c] == 0) {
					continue;
				}

				int boardX = c + x;
				int boardY = r + y;

				if (boardX < 0 || boardX >= COLS || boardY >= ROWS) {
					return false;
				}

				if (boardY >= 0 && board[boardY][boardX] != null) {
					return false;
				}
			}
		}

		return true;
	}

	private void clearBoard()
	{
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[r][c] = null;
			}
		}
	}
}
