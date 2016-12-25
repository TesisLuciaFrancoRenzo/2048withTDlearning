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
package ar.edu.unrc.game2048.experiments.configurations.ntuples;

import ar.edu.unrc.coeus.tdlearning.training.ntuple.SamplePointValue;
import ar.edu.unrc.coeus.utils.FunctionUtils;
import ar.edu.unrc.game2048.GameBoard;
import ar.edu.unrc.game2048.NTupleConfiguration2048;
import ar.edu.unrc.game2048.Tile;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

import java.util.ArrayList;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class ConfigNTupleBasicTanHSimplified_512
        extends NTupleConfiguration2048 {

    public static int maxReward = 100_000;
    public static int minReward = -100_000;
    private static int maxTile;
    private static int numSamples;

    /**
     * Configuración para jugar hasta 512, con función de activación Tangente Hiperbólica, y puntaje parcial.
     */
    public
    ConfigNTupleBasicTanHSimplified_512() {

        setTileToWinForTraining(512);

        activationFunction = FunctionUtils.TANH;
        derivedActivationFunction = FunctionUtils.TANH_DERIVED;
        concurrency = false;
        final double activationFunctionMax = 1;
        final double activationFunctionMin = -1;

        numSamples = 8;
        maxTile = 9;

        normOutput = new NormalizedField(NormalizationAction.Normalize, null, maxReward, minReward, activationFunctionMax, activationFunctionMin);

        nTuplesLength = new int[numSamples];
        for ( int i = 0; i < numSamples; i++ ) {
            nTuplesLength[i] = 4;
        }

        allSamplePointPossibleValues = new ArrayList<>();
        allSamplePointPossibleValues.add(null);
        for ( int i = 1; i <= maxTile; i++ ) {
            allSamplePointPossibleValues.add(new Tile((int) Math.pow(2, i)));
        }
    }

    /**
     * @return
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public
    ConfigNTupleBasicTanHSimplified_512 clone()
            throws CloneNotSupportedException {
        return (ConfigNTupleBasicTanHSimplified_512) super.clone();
    }

    @Override
    public
    double deNormalizeValueFromNeuralNetworkOutput( final Object value ) {
        return normOutput.deNormalize((double) value);
    }

    /**
     * @return máximo tile
     */
    public
    int getMaxTile() {
        return maxTile;
    }

    @Override
    public
    SamplePointValue[] getNTuple(
            final GameBoard board,
            final int nTupleIndex
    ) {
        switch ( nTupleIndex ) {
            // verticales
            case 0:
                return new SamplePointValue[] { board.tileAt(0, 0), board.tileAt(0, 1), board.tileAt(0, 2), board.tileAt(0, 3) };
            case 1:
                return new SamplePointValue[] { board.tileAt(1, 0), board.tileAt(1, 1), board.tileAt(1, 2), board.tileAt(1, 3) };
            case 2:
                return new SamplePointValue[] { board.tileAt(2, 0), board.tileAt(2, 1), board.tileAt(2, 2), board.tileAt(2, 3) };
            case 3:
                return new SamplePointValue[] { board.tileAt(3, 0), board.tileAt(3, 1), board.tileAt(3, 2), board.tileAt(3, 3) };
            // horizontales
            case 4:
                return new SamplePointValue[] { board.tileAt(0, 0), board.tileAt(1, 0), board.tileAt(2, 0), board.tileAt(3, 0) };
            case 5:
                return new SamplePointValue[] { board.tileAt(0, 1), board.tileAt(1, 1), board.tileAt(2, 1), board.tileAt(3, 1) };
            case 6:
                return new SamplePointValue[] { board.tileAt(0, 2), board.tileAt(1, 2), board.tileAt(2, 2), board.tileAt(3, 2) };
            case 7:
                return new SamplePointValue[] { board.tileAt(0, 3), board.tileAt(1, 3), board.tileAt(2, 3), board.tileAt(3, 3) };

            default:
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * @return número de muestras
     */
    public
    int getNumSamples() {
        return numSamples;
    }

    @Override
    public
    double normalizeValueToPerceptronOutput( final Object value ) {
        if ( (Double) value > maxReward ) {
            throw new IllegalArgumentException("value no puede ser mayor a maxReward=" + maxReward);
        }
        return normOutput.normalize((Double) value);
    }
}
