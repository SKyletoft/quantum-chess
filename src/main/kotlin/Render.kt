import org.openrndr.application
import org.openrndr.draw.Drawer
import org.openrndr.draw.loadImage
import org.openrndr.drawImage
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import org.openrndr.color.ColorRGBa as ColourRGBa
import org.openrndr.draw.ColorBuffer as ColourBuffer

val drawChessBoard: Drawer.() -> Unit = {
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
				Rectangle(80.0 * y.toDouble(), 80.0 * (7 - x).toDouble(), w, h),
			)
		}
	}
}
