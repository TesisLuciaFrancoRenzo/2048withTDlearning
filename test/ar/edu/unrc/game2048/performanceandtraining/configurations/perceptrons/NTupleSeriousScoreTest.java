
package ar.edu.unrc.game2048.performanceandtraining.configurations.perceptrons;

import ar.edu.unrc.game2048.Game2048;
import ar.edu.unrc.game2048.GameBoard;
import ar.edu.unrc.game2048.Tile;
import ar.edu.unrc.game2048.TileContainer;
import ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples.BasicScoreTanH;
import ar.edu.unrc.game2048.performanceandtraining.configurations.perceptrons.inputs.InputNtupleList;
import ar.edu.unrc.tdlearning.perceptron.learning.FunctionUtils;
import ar.edu.unrc.tdlearning.perceptron.ntuple.NTupleSystem;
import org.encog.neural.networks.BasicNetwork;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author franc
 */
public class NTupleSeriousScoreTest {

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    public NTupleSeriousScoreTest() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateNormalizedPerceptronInput method, of class
     * NTupleSeriousScore.
     */
    @Test
    public void testCalculateNormalizedPerceptronInput() {
        System.out.println("calculateNormalizedPerceptronInput");

        NTupleSeriousScore<BasicNetwork> nTupleConfiguration = new NTupleSeriousScore<>();
        TileContainer tileContainer = new TileContainer(nTupleConfiguration.getMaxTile());
        Tile[] randomB = {
            tileContainer.getTile(0), tileContainer.getTile(4), tileContainer.getTile(0), tileContainer.getTile(9),
            tileContainer.getTile(4), tileContainer.getTile(6), tileContainer.getTile(4), tileContainer.getTile(4),
            tileContainer.getTile(2), tileContainer.getTile(4), tileContainer.getTile(5), tileContainer.getTile(7),
            tileContainer.getTile(4), tileContainer.getTile(0), tileContainer.getTile(1), tileContainer.getTile(4)
        };
        Tile[] randomBoard = randomB;

        Game2048<BasicNetwork> game = new Game2048(nTupleConfiguration, null, (int) Math.pow(2, nTupleConfiguration.getMaxTile()), 0);
        GameBoard<BasicNetwork> board = new GameBoard(game, tileContainer);
        board.setTiles(randomBoard);

        InputNtupleList normalizedPerceptronInput = new InputNtupleList();
        nTupleConfiguration.calculateNormalizedPerceptronInput(board, normalizedPerceptronInput);

        //----------------------
        BasicScoreTanH nTupleConfiguration2 = new BasicScoreTanH();
        Game2048<BasicNetwork> game2 = new Game2048(null, nTupleConfiguration2, (int) Math.pow(2, nTupleConfiguration.getMaxTile()), 0);
        GameBoard<BasicNetwork> board2 = new GameBoard(game2, tileContainer);
        board2.setTiles(randomBoard);

        NTupleSystem nTupleSystem = new NTupleSystem(
                nTupleConfiguration.getAllSamplePointStates(),
                nTupleConfiguration.getnTuplesLenght(),
                FunctionUtils.tanh,
                FunctionUtils.derivatedTanh,
                false
        );

        int[] indexes = nTupleSystem.getComplexComputation(board2).getIndexes();
        assertTrue(normalizedPerceptronInput.getInternalSetSize() == indexes.length);

        for ( int i : indexes ) {
            assertTrue(normalizedPerceptronInput.get(i) == 1d);
        }
    }
}