/*
 * Copyright (C) 2016  Lucia Bressan <lucyluz333@gmial.com>,
 *                     Franco Pellegrini <francogpellegrini@gmail.com>,
 *                     Renzo Bianchini <renzobianchini85@gmail.com
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ar.edu.unrc.game2048;

import ar.edu.unrc.coeus.tdlearning.interfaces.IAction;
import ar.edu.unrc.coeus.tdlearning.interfaces.IState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class Game2048Test {

    private Game2048 game = null;

    /**
     *
     */
    @AfterEach
    public
    void tearDown() {
        if ( game != null ) {
            game = null;
        }
    }

    /**
     * Test of listAllPossibleActions method, of class Game2048.
     */
    @Test
    public
    void testListAllPossibleActions() {
        System.out.println("listAllPossibleActions");
        game = new Game2048(null, null, 2_048, false);

        //inicializamos un tablero terminal
        GameBoard board = new GameBoard(game);
        final Tile[][] terminalBoard = {
                { new Tile(128), new Tile(2), new Tile(8), new Tile(4) },
                { new Tile(2), new Tile(16), new Tile(32), new Tile(2) },
                { new Tile(8), new Tile(32), new Tile(2), new Tile(64) },
                { new Tile(2), new Tile(256), new Tile(32), new Tile(4) }
        };
        board.setTiles(terminalBoard);
        board.clearInterns(true);

        List< IAction > result = game.listAllPossibleActions(board);
        assertThat(result.isEmpty(), is(true));

        // =========================================== //
        //inicializamos un tablero no terminal
        board = new GameBoard(game);
        final Tile[][] fullNotTerminalBoard = {
                { new Tile(128), new Tile(2), new Tile(8), new Tile(4) },
                { new Tile(2), new Tile(16), new Tile(32), new Tile(2) },
                { new Tile(8), new Tile(32), new Tile(2), new Tile(64) },
                { new Tile(2), new Tile(256), new Tile(2), new Tile(4) }
        };
        board.setTiles(fullNotTerminalBoard);
        board.clearInterns(true);

        @SuppressWarnings( "MismatchedQueryAndUpdateOfCollection" ) final Set< IAction > expResult = new HashSet<>();
        expResult.add(Action.DOWN);
        expResult.add(Action.UP);

        IState state1 = game.computeAfterState(board, Action.DOWN); //para comparar luego
        assertThat(state1, notNullValue());

        result = game.listAllPossibleActions(board);
        assertThat(result.size(), is(2));

        Set< IAction > resultSet = new HashSet<>(game.listAllPossibleActions(board));
        for ( final IAction action : resultSet ) {
            assertThat(resultSet, hasItem(action));
        }

        //verificamos que si se llama al afterState antes de listAllPossibleActions
        // con los mismos tableros, devuelven instancias diferentes, pero con el mismo contenido lógico
        IState state2 = game.computeAfterState(board, Action.DOWN);
        assertThat(state2, not(sameInstance(state1)));
        assertThat(state1, is(state2));

        //verificamos que próximas llamadas a computeAfterState retorne valores ya calculados y no los calcule otra vez
        state1 = game.computeAfterState(board, Action.DOWN);
        assertThat(state1, notNullValue());
        state2 = game.computeAfterState(board, Action.DOWN);
        assertThat(state2, notNullValue());
        assertThat(state1, is(state2));

        state2 = game.computeAfterState(board, Action.UP);
        assertThat(state2, not(sameInstance(state1)));

        // =========================================== //
        //inicializamos un tablero con muchos movimientos terminal
        board = new GameBoard(game);
        final Tile[][] multipleMovesTerminalBoard = {
                { new Tile(128), new Tile(2), new Tile(8), new Tile(4) },
                { new Tile(2), null, new Tile(32), new Tile(2) },
                { new Tile(8), new Tile(32), new Tile(2), new Tile(64) },
                { new Tile(2), new Tile(256), new Tile(2), new Tile(4) }
        };
        board.setTiles(multipleMovesTerminalBoard);
        board.clearInterns(true);

        expResult.clear();
        expResult.add(Action.DOWN);
        expResult.add(Action.UP);
        expResult.add(Action.RIGHT);
        expResult.add(Action.LEFT);
        result = game.listAllPossibleActions(board);
        assertThat(result.size(), is(4));
        resultSet = new HashSet<>(game.listAllPossibleActions(board));
        for ( final IAction action : resultSet ) {
            assertThat(result, hasItem(action));
        }

        //verificamos que próximas llamadas a computeAfterState retorne valores ya calculados y no los calcule otra vez
        state1 = game.computeAfterState(board, Action.RIGHT);
        assertThat(state1, notNullValue());
        state2 = game.computeAfterState(board, Action.RIGHT);
        assertThat(state2, notNullValue());
        assertThat(state1, is(state2));

        state2 = game.computeAfterState(board, Action.LEFT);
        assertThat(state2, not(sameInstance(state1)));
    }

}
