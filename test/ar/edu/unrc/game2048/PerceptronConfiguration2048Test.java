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

import ar.edu.unrc.tdlearning.perceptron.interfaces.IsolatedComputation;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author franco
 */
public class PerceptronConfiguration2048Test {

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
    public PerceptronConfiguration2048Test() {
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
     * PerceptronConfiguration2048.
     */
    @Test
    public void testCalculateNormalizedPerceptronInput() {
        System.out.println("calculateNormalizedPerceptronInput");
        PerceptronConfiguration2048 instance = new PerceptronConfiguration2048Impl();
        //   instance.calculateNormalizedPerceptronInput(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class PerceptronConfiguration2048.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        PerceptronConfiguration2048 instance = new PerceptronConfiguration2048Impl();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeNumericRepresentationFor method, of class
     * PerceptronConfiguration2048.
     */
    @Test
    public void testComputeNumericRepresentationFor() {
        System.out.println("computeNumericRepresentationFor");
        Game2048 game = null;
        Object[] output = null;
        PerceptronConfiguration2048 instance = new PerceptronConfiguration2048Impl();
        IsolatedComputation<Double> expResult = null;
        IsolatedComputation<Double> result = instance.computeNumericRepresentationFor(game, output);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNeuralNetwork method, of class PerceptronConfiguration2048.
     */
    @Test
    public void testGetNeuralNetwork() {
        System.out.println("getNeuralNetwork");
        PerceptronConfiguration2048 instance = new PerceptronConfiguration2048Impl();
        Object expResult = null;
        Object result = instance.getNeuralNetwork();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNeuralNetwork method, of class PerceptronConfiguration2048.
     */
    @Test
    public void testSetNeuralNetwork() {
        System.out.println("setNeuralNetwork");
        Object neuralNetwork = null;
        PerceptronConfiguration2048 instance = new PerceptronConfiguration2048Impl();
        instance.setNeuralNetwork(neuralNetwork);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    public class PerceptronConfiguration2048Impl extends PerceptronConfiguration2048 {

        @Override
        public void calculateNormalizedPerceptronInput(GameBoard board, List normalizedPerceptronInput) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public IsolatedComputation<Double> computeNumericRepresentationFor(Game2048 game, Object[] output) {
            return null;
        }

        @Override
        public double denormalizeValueFromPerceptronOutput(Object value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public double getBoardReward(GameBoard board, int outputNeuron) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public double getFinalReward(GameBoard board, int outputNeuron) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public double normalizeValueToPerceptronOutput(Object value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     *
     */
    public class PerceptronConfiguration2048Impl extends PerceptronConfiguration2048 {

        /**
         *
         * @param board
         * @param normalizedPerceptronInput
         *
         * @return
         */
        @Override
        public IsolatedComputation calculateNormalizedPerceptronInput(GameBoard<NeuralNetworkClass> board, List<Double> normalizedPerceptronInput) {
        }

        /**
         *
         * @param game
         * @param output
         *
         * @return
         */
        @Override
        public IsolatedComputation<Double> computeNumericRepresentationFor(Game2048 game, Object[] output) {
            return null;
        }
    }

    /**
     *
     */
    public class PerceptronConfiguration2048Impl extends PerceptronConfiguration2048 {

        /**
         *
         * @param board
         * @param normalizedPerceptronInput
         *
         * @return
         */
        @Override
        public IsolatedComputation calculateNormalizedPerceptronInput(GameBoard<NeuralNetworkClass> board, List<Double> normalizedPerceptronInput) {
        }

        /**
         *
         * @param game
         * @param output
         *
         * @return
         */
        @Override
        public IsolatedComputation<Double> computeNumericRepresentationFor(Game2048 game, Object[] output) {
            return null;
        }
    }

    /**
     *
     */
    public class PerceptronConfiguration2048Impl extends PerceptronConfiguration2048 {

        /**
         *
         * @param board
         * @param normalizedPerceptronInput
         *
         * @return
         */
        public IsolatedComputation calculateNormalizedPerceptronInput(GameBoard<NeuralNetworkClass> board, List<Double> normalizedPerceptronInput) {
            return null;
        }

        /**
         *
         * @param game
         * @param output
         *
         * @return
         */
        @Override
        public IsolatedComputation<Double> computeNumericRepresentationFor(Game2048 game, Object[] output) {
            return null;
        }
    }

}
