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
import ar.edu.unrc.coeus.tdlearning.interfaces.IStatePerceptron;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class Game2048Test {

    private final TileContainer tileContainer;
    private       Game2048      game;

    /**
     *
     */
    public
    Game2048Test() {
        tileContainer = new TileContainer(17);
    }

    /**
     *
     */
    @BeforeClass
    public static
    void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static
    void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public
    void setUp() {
    }

    /**
     *
     */
    @After
    public
    void tearDown() {
        if (game != null) {
            game.dispose();
            game = null;
        }
    }

    /**
     * Test of listAllPossibleActions method, of class Game2048.
     */
    @SuppressWarnings("unchecked")
    @Test
    public
    void testListAllPossibleActions() {
        System.out.println("listAllPossibleActions");
        game = new Game2048(null, null, 2_048, 0);
        IStatePerceptron state1;
        IState           state2;

        //inicializamos un tablero terminal
        GameBoard board = new GameBoard(game, tileContainer);
        Tile[] terminalBoard = {tileContainer.getTile(7), tileContainer.getTile(1), tileContainer.
                getTile(3), tileContainer.getTile(2), tileContainer.getTile(1), tileContainer.getTile(4), tileContainer.
                getTile(5), tileContainer.getTile(1), tileContainer.getTile(3), tileContainer.getTile(5), tileContainer.
                getTile(1), tileContainer.getTile(6), tileContainer.getTile(1), tileContainer.getTile(8), tileContainer.
                getTile(5), tileContainer.getTile(2)};
        board.setTiles(terminalBoard);
        board.updateInternalState(true);

        ArrayList<IAction> result = game.listAllPossibleActions(board);
        assertThat(result.isEmpty(), is(true));

        // =========================================== //
        //inicializamos un tablero no terminal
        board = new GameBoard(game, tileContainer);
        Tile[] fullNotTerminalBoard = {tileContainer.getTile(7), tileContainer.getTile(1), tileContainer.
                getTile(3), tileContainer.getTile(2), tileContainer.getTile(1), tileContainer.getTile(4), tileContainer.
                getTile(5), tileContainer.getTile(1), tileContainer.getTile(3), tileContainer.getTile(5), tileContainer.
                getTile(1), tileContainer.getTile(6), tileContainer.getTile(1), tileContainer.getTile(8), tileContainer.
                getTile(1), tileContainer.getTile(2)};
        board.setTiles(fullNotTerminalBoard);
        board.updateInternalState(true);

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") Set<IAction> expResult = new HashSet<>();
        expResult.add(Action.down);
        expResult.add(Action.up);

        state1 = game.computeAfterState(board, Action.down); //para comparar luego
        assertThat(state1, notNullValue());

        result = game.listAllPossibleActions(board);
        assertThat(result.size(), is(2));

        Set<IAction> resultSet = new HashSet<>(game.
                                                           listAllPossibleActions(board));
        for (IAction action : resultSet) {
            assertThat(resultSet, hasItem(action));
        }

        //verificamos que si se llama al afterState antes de listAllPossibleActions
        // con los mismos tableros, devuelven instancias diferentes, pero con el mismo contenido logico
        state2 = game.computeAfterState(board, Action.down);
        Assert.assertNotSame(state1, state2);
        assertThat(state1, is((GameBoard) state2));

        //verificamos que proximas llamadas a computeafterState retorne valores ya calculados y no los calcule otra vez
        state1 = game.computeAfterState(board, Action.down);
        assertThat(state1, notNullValue());
        state2 = game.computeAfterState(board, Action.down);
        assertThat(state2, notNullValue());
        assertThat(state1, is(state2));

        state2 = game.computeAfterState(board, Action.up);
        Assert.assertNotSame(state1, state2);

        //verificamos que valores no calculados retornen null
        state1 = game.computeAfterState(board, Action.left);
        Assert.assertNull(state1);
        state1 = game.computeAfterState(board, Action.right);
        Assert.assertNull(state1);

        // =========================================== //
        //inicializamos un tablero con muchos movimientos terminal
        board = new GameBoard(game, tileContainer);
        Tile[] multipleMovesTerminalBoard = {tileContainer.getTile(7), tileContainer.getTile(1), tileContainer.
                getTile(3), tileContainer.getTile(2), tileContainer.getTile(1), tileContainer.getTile(0), tileContainer.
                getTile(5), tileContainer.getTile(1), tileContainer.getTile(3), tileContainer.getTile(5), tileContainer.
                getTile(1), tileContainer.getTile(6), tileContainer.getTile(1), tileContainer.getTile(8), tileContainer.
                getTile(1), tileContainer.getTile(2)};
        board.setTiles(multipleMovesTerminalBoard);
        board.updateInternalState(true);

        expResult.clear();
        expResult.add(Action.down);
        expResult.add(Action.up);
        expResult.add(Action.right);
        expResult.add(Action.left);
        result = game.listAllPossibleActions(board);
        assertThat(result.size(), is(4));
        resultSet = new HashSet<>(game.listAllPossibleActions(board));
        for (IAction action : resultSet) {
            assertThat(result, hasItem(action));
        }

        //verificamos que proximas llamadas a computeafterState retorne valores ya calculados y no los calcule otra vez
        state1 = game.computeAfterState(board, Action.right);
        assertThat(state1, notNullValue());
        state2 = game.computeAfterState(board, Action.right);
        assertThat(state2, notNullValue());
        assertThat(state1, is(state2));

        state2 = game.computeAfterState(board, Action.left);
        Assert.assertNotSame(state1, state2);
    }

}