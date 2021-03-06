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
package ar.edu.unrc.game2048.experiments.performance;

import ar.edu.unrc.coeus.interfaces.INeuralNetworkInterface;
import ar.edu.unrc.coeus.tdlearning.learning.TDLambdaLearning;
import ar.edu.unrc.coeus.tdlearning.training.ntuple.NTupleSystem;
import ar.edu.unrc.game2048.experiments.configurations.EncogConfiguration2048;
import ar.edu.unrc.game2048.experiments.configurations.LearningExperiment;
import ar.edu.unrc.game2048.experiments.configurations.librariesinterfaces.EncogExperimentInterface;
import ar.edu.unrc.game2048.experiments.configurations.perceptrons.ConfigPerceptronNTupleTanHSimplified_512;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationTANH;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.edu.unrc.coeus.tdlearning.learning.ELearningStyle.AFTER_STATE;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class ConcurrencyExperiment_Basic
        extends LearningExperiment {

    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static ConcurrencyConfig currentConfig;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static String            filePath;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static int               gamesToPlay;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static int               maxInnerLayers;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static int               maxNeuronQuantity;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static int               minNeuronQuantity;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static StringBuilder     outputForGraphicsResults;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static StringBuilder     outputResults;
    /**
     *
     */
    @SuppressWarnings( "PublicField" )
    public static int               samplesPerExperiment;

    /**
     * Ejecuta un experimento.
     *
     * @param trainConcurrency    true si debe entrenar usando concurrencia.
     * @param evaluateConcurrency true si debe evaluar decisiones usando concurrencia.
     */
    @SuppressWarnings( "HardcodedFileSeparator" )
    public static
    void experimentRun(
            final boolean trainConcurrency,
            final boolean evaluateConcurrency
    ) {
        for ( int innerLayerQuantity = 1; innerLayerQuantity <= maxInnerLayers; innerLayerQuantity++ ) {
            System.out.println("===============================================================================");
            System.out.println("Capas intermedias: " + innerLayerQuantity);
            outputForGraphicsResults.append("============ Capas intermedias: ")
                    .append(innerLayerQuantity)
                    .append(" - Training Concurrency: ")
                    .append(trainConcurrency)
                    .append(" - evaluating Concurrency: ")
                    .append(evaluateConcurrency)
                    .append(" =============\n");
            System.out.println("===============================================================================");

            final StringBuilder trainingOutput = new StringBuilder();
            final StringBuilder evaluateOutput = new StringBuilder();
            for ( int innerLayersNeuronQuantity = minNeuronQuantity; innerLayersNeuronQuantity <= maxNeuronQuantity; innerLayersNeuronQuantity++ ) {
                // Primer experimento, con 1 capa, en serie
                currentConfig = new ConcurrencyConfig();
                currentConfig.setConcurrencyInEvaluate(evaluateConcurrency);

                currentConfig.setConcurrencyInLayer(new boolean[innerLayerQuantity + 2]);
                for ( int i = 0; i < ( innerLayerQuantity + 1 ); i++ ) {
                    currentConfig.getConcurrencyInLayer()[i] = trainConcurrency;
                }
                currentConfig.getConcurrencyInLayer()[currentConfig.getConcurrencyInLayer().length - 1] = false;

                currentConfig.setAlphas(new double[innerLayerQuantity + 2]);
                for ( int i = 0; i < ( innerLayerQuantity + 2 ); i++ ) {
                    currentConfig.getAlphas()[i] = 0.0025;
                }

                currentConfig.setNeuronQuantityInLayer(new int[innerLayerQuantity + 2]);
                currentConfig.getNeuronQuantityInLayer()[0] = 64;
                for ( int i = 1; i <= innerLayerQuantity; i++ ) {
                    currentConfig.getNeuronQuantityInLayer()[i] = (int) Math.pow(2, innerLayersNeuronQuantity);
                }
                currentConfig.getNeuronQuantityInLayer()[currentConfig.getNeuronQuantityInLayer().length - 1] = 1;

                currentConfig.setActivationFunctionForEncog(new ActivationFunction[innerLayerQuantity + 1]);
                for ( int i = 0; i < ( innerLayerQuantity + 1 ); i++ ) {
                    currentConfig.getActivationFunctionForEncog()[i] = new ActivationTANH();
                }

                System.out.println("========================================================");
                System.out.println("Inicio Experimento:");
                System.out.println(currentConfig);
                System.out.println("========================================================");

                final StatisticCalculatorPerformance trainingStats     = new StatisticCalculatorPerformance(samplesPerExperiment);
                final StatisticCalculatorPerformance bestPossibleStats = new StatisticCalculatorPerformance(samplesPerExperiment);
                for ( int i = 1; i <= samplesPerExperiment; i++ ) {
                    System.out.println("Calculo de muestra N" + i);
                    long time = System.currentTimeMillis();
                    startStatistics(trainingStats, bestPossibleStats);
                    time = System.currentTimeMillis() - time;
                    System.out.println("Final de Calculo de muestra N" + i + " - demoró " + time + "ms");
                }
                final String[] resultsTraining     = trainingStats.computeBasicStatistics();
                final String   forSaveTraining     = trainingStats.toString();
                final String[] resultsBestPossible = bestPossibleStats.computeBasicStatistics();
                final String   forSaveBestPossible = bestPossibleStats.toString();

                System.out.println("====================================");
                System.out.println("Fin Experimento:");
                System.out.println(currentConfig);
                System.out.println("** Training DEMORÓ => " + resultsTraining[0]);
                System.out.println("** Best Possible Action DEMORÓ => " + resultsBestPossible[0]);
                System.out.println("====================================");

                outputResults.append("====================================\n");
                outputResults.append(currentConfig).append('\n');
                outputResults.append("muestras Training =>\t").append(forSaveTraining).append('\n');
                outputResults.append("resultados Training =>\t").append(resultsTraining[0]).append('\n');
                outputResults.append("muestras Best Possible Action =>\t").append(forSaveBestPossible).append('\n');
                outputResults.append("resultados Best Possible Action =>\t").append(resultsBestPossible[0]).append('\n');

                trainingOutput.append(innerLayersNeuronQuantity).append('\t').append(resultsTraining[1]).append('\n');
                evaluateOutput.append(innerLayersNeuronQuantity).append('\t').append(resultsBestPossible[1]).append('\n');
            }

            outputForGraphicsResults.append("Training: (neuron / avg time / min time / max time)").append('\n');
            outputForGraphicsResults.append(trainingOutput).append('\n');

            outputForGraphicsResults.append("Evaluating: (neuron / avg time / min time / max time)").append('\n');
            outputForGraphicsResults.append(evaluateOutput).append('\n').append('\n');
        }
    }

    public static
    void main( final String[] args ) {
        try {
            filePath = ( args.length == 0 ) ? ( ".." + File.separator + "Estadisticas" + File.separator ) : args[0];
            final File output = new File(filePath);
            if ( !output.exists() ) {
                output.mkdirs();
            }

            samplesPerExperiment = 10;
            gamesToPlay = 20;
            maxInnerLayers = 1;
            maxNeuronQuantity = 12;
            minNeuronQuantity = 2;

            outputResults = new StringBuilder();
            outputForGraphicsResults = new StringBuilder();
            final StringBuilder config = new StringBuilder();
            printConfig(config);
            System.out.println(config);

            printConfig(outputResults);
            printConfig(outputForGraphicsResults);

            long time = System.currentTimeMillis();
            experimentRun(false, true);
            experimentRun(true, true);

            time = System.currentTimeMillis() - time;
            System.out.println("Demoró = " + time + " ms.");
            outputResults.append("\n\n==========\nDemoró ").append(time).append(" ms.");
            outputForGraphicsResults.append("\n\n==========\nDemoró ").append(time).append(" ms.");

            try ( BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
                    filePath + "Concurrencia_Experimento_01.txt")), "UTF-8")) ) {
                out.write(outputResults.toString());
            }
            try ( BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(
                    filePath + "Concurrencia_Experimento_01_Calc.txt")), "UTF-8")) ) {
                out.write(outputForGraphicsResults.toString());
            }

            Toolkit.getDefaultToolkit().beep();
        } catch ( final Exception ex ) {
            Logger.getLogger(ConcurrencyExperiment_Basic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private static
    void printConfig( final StringBuilder outputResults ) {
        outputResults.append("====================================").append('\n');
        outputResults.append("samplesPerExperiment = ").append(samplesPerExperiment).append('\n');
        outputResults.append("gamesToPlay = ").append(gamesToPlay).append('\n');
        outputResults.append("maxInnerLayers = ").append(maxInnerLayers).append('\n');
        outputResults.append("maxNeuronQuantity = ").append(maxNeuronQuantity).append(" (").append(Math.pow(2, maxNeuronQuantity))
                .append(')')
                .append('\n');
        outputResults.append("minNeuronQuantity = ").append(minNeuronQuantity).append(" (").append(Math.pow(2, minNeuronQuantity))
                .append(')')
                .append('\n');
        outputResults.append("====================================").append('\n');
    }

    private static
    void startStatistics(
            final StatisticCalculatorPerformance trainingStats,
            final StatisticCalculatorPerformance bestPossibleStats
    ) {
        final LearningExperiment experiment = new ConcurrencyExperiment_Basic();
        experiment.setAlpha(currentConfig.getAlphas());
        experiment.setConcurrencyInLayer(currentConfig.getConcurrencyInLayer());
        experiment.setComputeBestPossibleActionConcurrently(currentConfig.isConcurrencyInEvaluate());
        experiment.setLearningRateAdaptationToAnnealing(500_000);
        experiment.setLambda(0.7);
        experiment.setEligibilityTraceLength(-1);
        experiment.setGamma(1);
        experiment.setExplorationRateToFixed(0);
        experiment.setGamesToPlay(gamesToPlay);
        experiment.setSaveEvery(10_000); //no se guarda nada
        experiment.setSaveBackupEvery(10_000); //no se hacen backup
        experiment.setInitializePerceptronRandomized(false);
        experiment.setComputeBestPossibleActionConcurrently(false);
        experiment.createLogs(false);
        experiment.setStatisticsOnly(false);
        experiment.setRunStatisticsForBackups(false);
        experiment.setGamesToPlayPerThreadForStatistics(0);
        experiment.setSimulationsForStatistics(0);
        experiment.start(-1, filePath, false, filePath, false);

        bestPossibleStats.addSample(experiment.getBestPossibleActionTimesAverage());
        trainingStats.addSample(experiment.getTrainingTimesAverage());
    }

    @Override
    public
    void initialize() {
        setTileToWinForTraining(2_048);
        if ( getExperimentName() == null ) {
            setExperimentName("ConcurrencyTimes");
        }
        setNeuralNetworkName(getExperimentName());
        final EncogConfiguration2048 config = new ConfigPerceptronNTupleTanHSimplified_512(true);
        config.setNeuronQuantityInLayer(currentConfig.getNeuronQuantityInLayer());
        config.setActivationFunctionForEncog(currentConfig.getActivationFunctionForEncog());
        setNeuralNetworkInterfaceFor2048(new EncogExperimentInterface(config));
    }

    @Override
    public
    TDLambdaLearning instanceOfTdLearningImplementation(
            final INeuralNetworkInterface perceptronInterface
    ) {
        return new TDLambdaLearning(perceptronInterface,
                AFTER_STATE,
                getAlpha(),
                getLambda(),
                isReplaceEligibilityTraces(),
                getGamma(),
                getConcurrencyInLayer(),
                new Random(),
                true);
    }

    @Override
    public
    TDLambdaLearning instanceOfTdLearningImplementation(
            final NTupleSystem nTupleSystem
    ) {
        return null;
    }

}
