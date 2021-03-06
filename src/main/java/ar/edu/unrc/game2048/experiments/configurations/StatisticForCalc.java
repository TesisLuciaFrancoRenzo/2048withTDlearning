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
package ar.edu.unrc.game2048.experiments.configurations;

import java.util.List;

/**
 * Estructura para guardar los datos obtenidos en un cálculo estadístico.
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class StatisticForCalc {

    private double         maxScore       = 0.0;
    private double         maxTurn        = 0.0;
    private double         meanScore      = 0.0;
    private double         meanTurn       = 0.0;
    private double         minScore       = 0.0;
    private double         minTurn        = 0.0;
    private List< Double > tileStatistics = null;
    private double         winRate        = 0.0;

    /**
     * @return puntaje máximo.
     */
    public
    double getMaxScore() {
        return maxScore;
    }

    /**
     * @param maxScore nuevo puntaje máximo.
     */
    public
    void setMaxScore( final double maxScore ) {
        this.maxScore = maxScore;
    }

    /**
     * @return mayor turno ganador.
     */
    public
    double getMaxTurn() {
        return maxTurn;
    }

    /**
     * @param maxTurn nuevo mayor turno ganador.
     */
    public
    void setMaxTurn( final double maxTurn ) {
        this.maxTurn = maxTurn;
    }

    /**
     * @return puntaje medio.
     */
    public
    double getMeanScore() {
        return meanScore;
    }

    /**
     * @param meanScore nuevo puntaje medio.
     */
    public
    void setMeanScore( final double meanScore ) {
        this.meanScore = meanScore;
    }

    /**
     * @return turno ganador medio.
     */
    public
    double getMeanTurn() {
        return meanTurn;
    }

    /**
     * @param meanTurn nuevo turno ganador medio.
     */
    public
    void setMeanTurn( final double meanTurn ) {
        this.meanTurn = meanTurn;
    }

    /**
     * @return menor puntaje.
     */
    public
    double getMinScore() {
        return minScore;
    }

    /**
     * @param minScore nuevo menor puntaje.
     */
    public
    void setMinScore( final double minScore ) {
        this.minScore = minScore;
    }

    /**
     * @return menor turno.
     */
    public
    double getMinTurn() {
        return minTurn;
    }

    /**
     * @param minTurn nuevo menor turno.
     */
    public
    void setMinTurn( final double minTurn ) {
        this.minTurn = minTurn;
    }

    /**
     * @return lista con la cantidad de veces que se alcanzo cada Tile, siendo el valor del tile = 2^índice.
     */
    public
    List< Double > getTileStatistics() {
        return tileStatistics;
    }

    /**
     * @param tileStatistics nueva lista con la cantidad de veces que se alcanzo cada Tile, siendo el valor del tile = 2^índice.
     */
    public
    void setTileStatistics( final List< Double > tileStatistics ) {
        this.tileStatistics = tileStatistics;
    }

    /**
     * @return tasa de partidas ganadoras.
     */
    public
    double getWinRate() {
        return winRate;
    }

    /**
     * @param winRate nueva tasa de partidas ganadoras.
     */
    public
    void setWinRate( final double winRate ) {
        this.winRate = winRate;
    }
}
