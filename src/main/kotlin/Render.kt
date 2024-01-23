import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.color.ColorRGBa as ColourRGBa
import org.openrndr.draw.ColorBuffer as ColourBuffer

fun screenToMap(v: Vector2): Coord =
	Pair(
		7 - (v.y / 80).toInt(),
		(v.x / 80).toInt(),
	)

fun mapToScreen(c: Coord): Vector2 =
	Vector2(
		80.0 * c.second.toDouble(),
		80.0 * (7 - c.first).toDouble(),
	)

fun Drawer.drawChessBoard() {
	this.clear(ColourRGBa.fromHex(0xECD3B9))
	this.fill = ColourRGBa.fromHex(0xA16F5A)
	val squares =
		listOf(
			Vector2(0.0, 0.0),
			Vector2(2.0, 0.0),
			Vector2(4.0, 0.0),
			Vector2(6.0, 0.0),
			Vector2(1.0, 1.0),
			Vector2(3.0, 1.0),
			Vector2(5.0, 1.0),
			Vector2(7.0, 1.0),
			Vector2(0.0, 2.0),
			Vector2(2.0, 2.0),
			Vector2(4.0, 2.0),
			Vector2(6.0, 2.0),
			Vector2(1.0, 3.0),
			Vector2(3.0, 3.0),
			Vector2(5.0, 3.0),
			Vector2(7.0, 3.0),
			Vector2(0.0, 4.0),
			Vector2(2.0, 4.0),
			Vector2(4.0, 4.0),
			Vector2(6.0, 4.0),
			Vector2(1.0, 5.0),
			Vector2(3.0, 5.0),
			Vector2(5.0, 5.0),
			Vector2(7.0, 5.0),
			Vector2(0.0, 6.0),
			Vector2(2.0, 6.0),
			Vector2(4.0, 6.0),
			Vector2(6.0, 6.0),
			Vector2(1.0, 7.0),
			Vector2(3.0, 7.0),
			Vector2(5.0, 7.0),
			Vector2(7.0, 7.0),
		).map {
			Vector2(
				it.x * this.context.width.toDouble() / 8,
				it.y * this.context.height.toDouble() / 8,
			)
		}

	// Beacuse apparently 0 still draws an outline?
	this.strokeWeight = -1.0
	this.rectangles(squares, this.context.width.toDouble() / 8, this.context.height.toDouble() / 8)
	this.strokeWeight = 1.0
}

fun getSprite(
	x: Int,
	y: Int,
): Rectangle {
	val spriteSize = 334.0
	return Rectangle(
		x * spriteSize,
		y * spriteSize,
		spriteSize,
		spriteSize,
	)
}

val whiteKing = getSprite(0, 0)
val whiteQueen = getSprite(1, 0)
val whiteBishop = getSprite(2, 0)
val whiteKnight = getSprite(3, 0)
val whiteRook = getSprite(4, 0)
val whitePawn = getSprite(5, 0)
val blackKing = getSprite(0, 1)
val blackQueen = getSprite(1, 1)
val blackBishop = getSprite(2, 1)
val blackKnight = getSprite(3, 1)
val blackRook = getSprite(4, 1)
val blackPawn = getSprite(5, 1)

public fun Piece.sprite(): Rectangle {
	if (this.colour == Colour.White && this.type == PieceType.King)      return whiteKing
	if (this.colour == Colour.White && this.type == PieceType.Queen)     return whiteQueen
	if (this.colour == Colour.White && this.type == PieceType.Bishop)    return whiteBishop
	if (this.colour == Colour.White && this.type == PieceType.Knight)    return whiteKnight
	if (this.colour == Colour.White && this.type == PieceType.Rook)      return whiteRook
	if (this.colour == Colour.White && this.type == PieceType.Pawn)      return whitePawn
	if (this.colour == Colour.White && this.type == PieceType.FirstPawn) return whitePawn
	if (this.colour == Colour.Black && this.type == PieceType.King)      return blackKing
	if (this.colour == Colour.Black && this.type == PieceType.Queen)     return blackQueen
	if (this.colour == Colour.Black && this.type == PieceType.Bishop)    return blackBishop
	if (this.colour == Colour.Black && this.type == PieceType.Knight)    return blackKnight
	if (this.colour == Colour.Black && this.type == PieceType.Rook)      return blackRook
	return blackPawn
}

fun Drawer.drawBoardState(
	board: Board,
	spriteSheet: ColourBuffer,
	w: Double,
	h: Double,
) {
	for (x in 0..<8) {
		for (y in 0..<8) {
			val piece = board[x, y]
			if (piece == null) continue

			this.image(
				spriteSheet,
				piece.sprite(),
				Rectangle(mapToScreen(Pair(x, y)), w, h),
			)
		}
	}
}
