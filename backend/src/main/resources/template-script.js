class Surroundings{up(){}down(){}left(){}right(){}}class Moves{UP;LEFT;RIGHT;DOWN;}class FieldObjects{FLAG;PLAYER;EMPTY;BORDER;}

/**
 * content of the fields surrounding your avatar
 */
const surroundings = new Surroundings();
/**
 * return one of these to make a move in the desired direction
 */
const moves = new Moves();
/**
 * possible content of Fields
 * FLAG: Goal if you move to the flag field you win
 * EMPTY: an empty field you can move to
 * PLAYER: another player, you can't move to this field
 * BOARDER: this field is outside the map you cant move here
 */
const fieldObjects = new FieldObjects();

(function () {
    // Replace the content of this function with your code 😉
    if (surroundings.down() === fieldObjects.FLAG) {
        return moves.DOWN;
    }
    var random = Math.floor(Math.random() * (4 - 1 + 1) + 1)

    if (random === 1) {
        return moves.UP;
    }
    if (random === 2) {
        return moves.DOWN;
    }
    if (random === 3) {
        return moves.LEFT;
    }
    if (random === 4) {
        return moves.RIGHT;
    }
})
