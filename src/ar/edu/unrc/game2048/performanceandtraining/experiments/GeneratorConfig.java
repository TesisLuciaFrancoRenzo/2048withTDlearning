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
package ar.edu.unrc.game2048.performanceandtraining.experiments;

/**
 * Configuración de un generador de Tests.
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public class GeneratorConfig {

    private final double alpha;
    private final int annealingAlpha;
    private final double explorationRate;
    private final double gamma;
    private final double lambda;
    private final int number;
    private final boolean resetTraces;

    /**
     *
     * @param alpha
     * @param annealingAlpha
     * @param lambda
     * @param gamma
     * @param explorationRate
     * @param resetTraces
     * @param number
     */
    public GeneratorConfig(double alpha,
            int annealingAlpha,
            double lambda,
            double gamma,
            double explorationRate,
            boolean resetTraces,
            int number) {
        this.alpha = alpha;
        this.annealingAlpha = annealingAlpha;
        this.lambda = lambda;
        this.gamma = gamma;
        this.explorationRate = explorationRate;
        this.resetTraces = resetTraces;
        this.number = number;
    }

    /**
     * @return alpha
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * @return annealingAlpha
     */
    public int getAnnealingAlpha() {
        return annealingAlpha;
    }

    /**
     * @return explorationRate
     */
    public double getExplorationRate() {
        return explorationRate;
    }

    /**
     * @return gamma
     */
    public double getGamma() {
        return gamma;
    }

    /**
     * @return lambda
     */
    public double getLambda() {
        return lambda;
    }

    /**
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return resetTraces
     */
    public boolean isResetTraces() {
        return resetTraces;
    }

}
