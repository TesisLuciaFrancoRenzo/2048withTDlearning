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
package ar.edu.unrc.game2048.performanceandtraining.configurations.learning.encog;

import ar.edu.unrc.game2048.performanceandtraining.configurations.LearningExperiment;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public class TestGeneratorALL {

    /**
     *
     * @param experiment
     * @param statisticsOnly
     * @param runStatisticForRandom
     * @param runStatisticsForBackups
     * @param createLogs
     * @param lambda
     * @param alpha
     * @param gamesToPlay
     * @param saveEvery
     * @param gamesToPlayPerThreadForStatistics
     * @param simulationsForStatistics
     * @param filePath
     */
    public static void configAndExcecute(LearningExperiment experiment, boolean statisticsOnly, boolean runStatisticForRandom, boolean runStatisticsForBackups, boolean createLogs, double lambda, double alpha, int gamesToPlay, int saveEvery, int gamesToPlayPerThreadForStatistics, int simulationsForStatistics, String filePath) {
        experiment.setStatisticsOnly(statisticsOnly);
        experiment.setRunStatisticForRandom(runStatisticForRandom);
        experiment.setRunStatisticsForBackups(runStatisticsForBackups);
        experiment.createLogs(createLogs);
        experiment.setLambda(lambda);
        experiment.setGamma(1);
        experiment.setExplorationRateToFixed(0);

        experiment.setResetEligibilitiTraces(true);
        experiment.setLearningRateAdaptationToFixed();
        experiment.setGamesToPlay(gamesToPlay);
        experiment.setSaveBackupEvery(saveEvery);
        experiment.setGamesToPlayPerThreadForStatistics(gamesToPlayPerThreadForStatistics);
        experiment.setSimulationsForStatistics(simulationsForStatistics);
        experiment.start(filePath, 0);
    }

    /**
     *
     * @param args <p>
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String filePath;
        if ( args.length == 0 ) {
            filePath
                    = ".." + File.separator
                    + "Perceptrones ENTRENADOS" + File.separator;
        } else {
            filePath = args[0];
        }

        List<Double> lambdaList = new LinkedList<>();
        List<Double> alphaList = new LinkedList<>();

        //============================== configuraciones manuales ==================================
        boolean statistics = true;
//        boolean statistics = false;

        int gamesToPlay = 10_000;
        int saveEvery = 1_000;

//        lambdaList.add(0d);
//        lambdaList.add(0.1d);
//        lambdaList.add(0.2d);
//        lambdaList.add(0.3d);
//        lambdaList.add(0.4d);
//        lambdaList.add(0.5d);
        lambdaList.add(0.6d);
//        lambdaList.add(0.7d);
//        lambdaList.add(0.8d);
//        lambdaList.add(0.9d);
//        lambdaList.add(1d);
        //-------------------------------------
        alphaList.add(0.001d);
//        alphaList.add(0.1d);
//        alphaList.add(0.025d);
//        alphaList.add(0.05d);
//        alphaList.add(0.1d);
//        alphaList.add(0.2d);
//        alphaList.add(0.3d);
//        alphaList.add(0.4d);
//        alphaList.add(0.5d);
//        alphaList.add(0.6d);
//        alphaList.add(0.7d);
//        alphaList.add(0.8d);
//        alphaList.add(0.9d);
//        alphaList.add(1d);

        boolean createLogs = false;

        //============================== fin de configuraciones manuales ==================================
        boolean statisticsOnly;
        boolean runStatisticForRandom;
        boolean runStatisticsForBackups;
        int gamesToPlayPerThreadForStatistics;
        int simulationsForStatistics;

        if ( statistics ) {
            statisticsOnly = true;
            runStatisticForRandom = true;
            runStatisticsForBackups = true;
            gamesToPlayPerThreadForStatistics = 10_000;
            simulationsForStatistics = 8;
        } else {
            statisticsOnly = false;
            runStatisticForRandom = false;
            runStatisticsForBackups = false;
            gamesToPlayPerThreadForStatistics = 0;
            simulationsForStatistics = 0;
        }

        runAllConfigs("Experiment_01", new Experiment_01(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_02", new Experiment_02(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_03", new Experiment_03(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_04", new Experiment_04(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_05", new Experiment_05(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_06", new Experiment_06(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_07", new Experiment_07(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
        runAllConfigs("Experiment_08", new Experiment_08(), alphaList, lambdaList, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);

    }

    /**
     *
     * @param experimentName
     * @param experiment
     * @param alphaList
     * @param lambdaList
     * @param statisticsOnly
     * @param runStatisticForRandom
     * @param runStatisticsForBackups
     * @param createLogs
     * @param gamesToPlay
     * @param saveEvery
     * @param gamesToPlayPerThreadForStatistics
     * @param simulationsForStatistics
     * @param filePath
     */
    public static void runAllConfigs(String experimentName, LearningExperiment experiment, List<Double> alphaList, List<Double> lambdaList, boolean statisticsOnly, boolean runStatisticForRandom, boolean runStatisticsForBackups, boolean createLogs, int gamesToPlay, int saveEvery, int gamesToPlayPerThreadForStatistics, int simulationsForStatistics, String filePath) {
        alphaList.stream().forEach((alpha) -> {
            lambdaList.stream().forEach((lambda) -> {
                experiment.setExperimentName(experimentName + "-alpha_" + alpha + "-lambda_" + lambda);
                configAndExcecute(experiment, statisticsOnly, runStatisticForRandom, runStatisticsForBackups, createLogs, lambda, alpha, gamesToPlay, saveEvery, gamesToPlayPerThreadForStatistics, simulationsForStatistics, filePath);
            });
        });
    }
}
