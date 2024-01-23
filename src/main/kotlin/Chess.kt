enum class PieceType {
	King,
	Queen,
	Bishop,
	Knight,
	Rook,
	FirstPawn,
	Pawn,
	;

	companion object {
		val rookMoves: Array<Iterable<Pair<Int, Int>>> =
			arrayOf(
				Pair(1, 0),
				Pair(-1, 0),
				Pair(0, 1),
				Pair(0, -1),
			)
				.map { (x, y) -> (1..8).map { i -> Pair(x * i, y * i) } }
				.toTypedArray()

		val knightMoves: Array<Iterable<Pair<Int, Int>>> =
			arrayOf(
				listOf(Pair(1, 2)),
				listOf(Pair(2, 1)),
				listOf(Pair(-1, 2)),
				listOf(Pair(-2, 1)),
				listOf(Pair(1, -2)),
				listOf(Pair(2, -1)),
				listOf(Pair(-1, -2)),
				listOf(Pair(-2, -1)),
			)

		val bishopMoves: Array<Iterable<Pair<Int, Int>>> =
			arrayOf(
				Pair(1, 1),
				Pair(-1, 1),
				Pair(1, -1),
				Pair(-1, -1),
			)
				.map { (x, y) -> (1..8).map { i -> Pair(x * i, y * i) } }
				.toTypedArray()

		val queenMoves = bishopMoves + rookMoves

		val kingMoves: Array<Iterable<Pair<Int, Int>>> =
			arrayOf(
				listOf(Pair(-1, -1)),
				listOf(Pair(0, -1)),
				listOf(Pair(1, -1)),
				listOf(Pair(-1, 0)),
				listOf(Pair(1, 0)),
				listOf(Pair(-1, 1)),
				listOf(Pair(0, 1)),
				listOf(Pair(1, 1)),
			)
	}

	public fun moves(): Array<Iterable<Pair<Int, Int>>> {
		when (this) {
			Pawn -> throw Exception("todo")
			FirstPawn -> throw Exception("todo")
			Rook -> return rookMoves
			Knight -> return knightMoves
			Bishop -> return bishopMoves
			Queen -> return queenMoves
			King -> return kingMoves
		}
	}
}

enum class Colour {
	White,
	Black,
}

data class Piece(val type: PieceType, val colour: Colour, val identifier: Int)

fun inBounds(p: Coord): Boolean = inBounds(p.first, p.second)

fun inBounds(
	x: Int,
	y: Int,
): Boolean = 0 <= x && x < 8 && 0 <= y && y < 8

data class Board(var pieces: Array<Piece?>) {
	companion object {
		val startingPosition: Board =
			run {
				var board = Board(arrayOfNulls(64))

				board[0, 0] = Piece(PieceType.Rook, Colour.White, 0)
				board[0, 1] = Piece(PieceType.Knight, Colour.White, 0)
				board[0, 2] = Piece(PieceType.Bishop, Colour.White, 0)
				board[0, 3] = Piece(PieceType.Queen, Colour.White, 0)
				board[0, 4] = Piece(PieceType.King, Colour.White, 0)
				board[0, 5] = Piece(PieceType.Bishop, Colour.White, 1)
				board[0, 6] = Piece(PieceType.Knight, Colour.White, 1)
				board[0, 7] = Piece(PieceType.Rook, Colour.White, 1)

				board[1, 0] = Piece(PieceType.FirstPawn, Colour.White, 0)
				board[1, 1] = Piece(PieceType.FirstPawn, Colour.White, 1)
				board[1, 2] = Piece(PieceType.FirstPawn, Colour.White, 2)
				board[1, 3] = Piece(PieceType.FirstPawn, Colour.White, 3)
				board[1, 4] = Piece(PieceType.FirstPawn, Colour.White, 4)
				board[1, 5] = Piece(PieceType.FirstPawn, Colour.White, 5)
				board[1, 6] = Piece(PieceType.FirstPawn, Colour.White, 6)
				board[1, 7] = Piece(PieceType.FirstPawn, Colour.White, 7)

				board[7, 0] = Piece(PieceType.Rook, Colour.Black, 0)
				board[7, 1] = Piece(PieceType.Knight, Colour.Black, 0)
				board[7, 2] = Piece(PieceType.Bishop, Colour.Black, 0)
				board[7, 3] = Piece(PieceType.Queen, Colour.Black, 0)
				board[7, 4] = Piece(PieceType.King, Colour.Black, 0)
				board[7, 5] = Piece(PieceType.Bishop, Colour.Black, 1)
				board[7, 6] = Piece(PieceType.Knight, Colour.Black, 1)
				board[7, 7] = Piece(PieceType.Rook, Colour.Black, 1)

				board[6, 0] = Piece(PieceType.FirstPawn, Colour.Black, 0)
				board[6, 1] = Piece(PieceType.FirstPawn, Colour.Black, 1)
				board[6, 2] = Piece(PieceType.FirstPawn, Colour.Black, 2)
				board[6, 3] = Piece(PieceType.FirstPawn, Colour.Black, 3)
				board[6, 4] = Piece(PieceType.FirstPawn, Colour.Black, 4)
				board[6, 5] = Piece(PieceType.FirstPawn, Colour.Black, 5)
				board[6, 6] = Piece(PieceType.FirstPawn, Colour.Black, 6)
				board[6, 7] = Piece(PieceType.FirstPawn, Colour.Black, 7)

				board
			}
	}

	operator fun get(i: Pair<Int, Int>): Piece? {
		val x = i.first
		val y = i.second
		return this[x, y]
	}

	operator fun get(
		x: Int,
		y: Int,
	): Piece? {
		if (x < 0 || x >= 8) {
			throw Exception("Out of bounds!")
		}
		if (y < 0 || y >= 8) {
			throw Exception("Out of bounds!")
		}
		return this.pieces[y * 8 + x]
	}

	operator fun set(
		i: Pair<Int, Int>,
		p: Piece?,
	) {
		val x = i.first
		val y = i.second
		this[x, y] = p
	}

	operator fun set(
		x: Int,
		y: Int,
		p: Piece?,
	) {
		if (x < 0 || x >= 8) {
			throw Exception("Out of bounds!")
		}
		if (y < 0 || y >= 8) {
			throw Exception("Out of bounds!")
		}
		this.pieces[y * 8 + x] = p
	}
}

fun Coord.add(rhs: Coord): Coord {
	return Pair(
		this.first + rhs.first,
		this.second + rhs.second,
	)
}

fun moves(
	board: Board,
	from: Coord,
): List<Coord> {
	val piece = board[from]
	if (piece == null) {
		throw Exception("Moving non-existant piece")
	}
	return piece.type
		.moves()
		.map { dir ->
			var hitEnemy = false
			dir
				.map { it.add(from) }
				.takeWhile {
					when {
						!inBounds(it) -> false
						board[it] == null -> true
						board[it]!!.colour != piece.colour && !hitEnemy -> {
							hitEnemy = true
							true
						}
						else -> false
					}
				}
		}
		.flatMap { it }
}

fun move(
	board: Board,
	from: Coord,
	to: Coord,
) {
	val piece = board[from]
	if (piece == null) {
		throw Exception("Moving non-existant piece")
	}
	board[from] = null
	board[to] = piece
}

fun moveTwo(
	board: Board,
	from: Coord,
	to1: Coord,
	to2: Coord,
) {
	val piece = board[from]
	if (piece == null) {
		throw Exception("Moving non-existant piece")
	}
	board[from] = null

	board[to1] = piece
	board[to2] = piece
}
