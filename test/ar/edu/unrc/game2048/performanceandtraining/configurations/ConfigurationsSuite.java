/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.game2048.performanceandtraining.configurations;

import ar.edu.unrc.game2048.performanceandtraining.configurations.learning.LearningSuite;
import ar.edu.unrc.game2048.performanceandtraining.configurations.libraries.LibrariesSuite;
import ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples.NtuplesSuite;
import ar.edu.unrc.game2048.performanceandtraining.configurations.perceptrons.PerceptronsSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author franco
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( {LearningSuite.class, PerceptronsSuite.class, LibrariesSuite.class, INeuralNetworkInterfaceFor2048Test.class, StatisticForCalcTest.class, VisualExperimentTest.class, LearningExperimentTest.class, StatisticExperimentTest.class, ThreadResultTest.class, NtuplesSuite.class})
public class ConfigurationsSuite {

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

}