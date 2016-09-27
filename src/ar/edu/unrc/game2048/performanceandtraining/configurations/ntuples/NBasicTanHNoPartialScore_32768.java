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
package ar.edu.unrc.game2048.performanceandtraining.configurations.ntuples;

import ar.edu.unrc.coeus.tdlearning.training.ntuple.SamplePointValue;
import ar.edu.unrc.coeus.tdlearning.utils.FunctionUtils;
import ar.edu.unrc.game2048.GameBoard;
import ar.edu.unrc.game2048.NTupleConfiguration2048;
import ar.edu.unrc.game2048.Tile;
import java.util.ArrayList;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public class NBasicTanHNoPartialScore_32768 extends NTupleConfiguration2048 {

    private final int maxReward = 500_000;
    private final int minReward = -500_000;

    /**
     *
     */
    public NBasicTanHNoPartialScore_32768() {
        this.activationFunction = FunctionUtils.TANH;
        this.derivatedActivationFunction = FunctionUtils.TANH_DERIVATED;
        double activationFunctionMax = 1;
        double activationFunctionMin = -1;
        this.concurrency = false;

        normOutput = new NormalizedField(NormalizationAction.Normalize,
                null, maxReward, minReward, activationFunctionMax,
                activationFunctionMin);

        nTuplesLenght = new int[17];
        for ( int i = 0; i < 17; i++ ) {
            nTuplesLenght[i] = 4;
        }

        int maxTile = 15;
        this.allSamplePointPossibleValues = new ArrayList<>();
        for ( int i = 0; i <= maxTile; i++ ) {
            allSamplePointPossibleValues.add(new Tile(i));
        }
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double denormalizeValueFromNeuralNetworkOutput(Object value) {
        return normOutput.deNormalize((double) value);
    }

    @Override
    public double getBoardReward(GameBoard board,
            int outputNeuron) {
        return 0;
    }

    @Override
    public double getFinalReward(GameBoard board,
            int outputNeuron) {
        return board.getGame().getScore();
    }

    @Override
    public SamplePointValue[] getNTuple(GameBoard board,
            int nTupleIndex) {
        switch ( nTupleIndex ) {
            // verticales
            case 0: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 0),
                            board.tileAt(0, 1),
                            board.tileAt(0, 2),
                            board.tileAt(0, 3)};
                return sample;
            }
            case 1: {
                SamplePointValue[] sample
                        = {board.tileAt(1, 0),
                            board.tileAt(1, 1),
                            board.tileAt(1, 2),
                            board.tileAt(1, 3)};
                return sample;
            }
            case 2: {
                SamplePointValue[] sample
                        = {board.tileAt(2, 0),
                            board.tileAt(2, 1),
                            board.tileAt(2, 2),
                            board.tileAt(2, 3)};
                return sample;
            }
            case 3: {
                SamplePointValue[] sample
                        = {board.tileAt(3, 0),
                            board.tileAt(3, 1),
                            board.tileAt(3, 2),
                            board.tileAt(3, 3)};
                return sample;
            }
            // horizontales
            case 4: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 0),
                            board.tileAt(1, 0),
                            board.tileAt(2, 0),
                            board.tileAt(3, 0)};
                return sample;
            }
            case 5: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 1),
                            board.tileAt(1, 1),
                            board.tileAt(2, 1),
                            board.tileAt(3, 1)};
                return sample;
            }
            case 6: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 2),
                            board.tileAt(1, 2),
                            board.tileAt(2, 2),
                            board.tileAt(3, 2)};
                return sample;
            }
            case 7: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 3),
                            board.tileAt(1, 3),
                            board.tileAt(2, 3),
                            board.tileAt(3, 3)};
                return sample;
            }
            // cuadrados
            // primera fila de rectangulos
            case 8: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 0),
                            board.tileAt(0, 1),
                            board.tileAt(1, 1),
                            board.tileAt(1, 0)};
                return sample;
            }
            case 9: {
                SamplePointValue[] sample
                        = {board.tileAt(1, 0),
                            board.tileAt(1, 1),
                            board.tileAt(2, 1),
                            board.tileAt(2, 0)};
                return sample;
            }
            case 10: {
                SamplePointValue[] sample
                        = {board.tileAt(2, 0),
                            board.tileAt(2, 1),
                            board.tileAt(3, 1),
                            board.tileAt(3, 0)};
                return sample;
            }
            //segunda fila de rectangulos
            case 11: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 1),
                            board.tileAt(0, 2),
                            board.tileAt(1, 2),
                            board.tileAt(1, 1)};
                return sample;
            }
            case 12: {
                SamplePointValue[] sample
                        = {board.tileAt(1, 1),
                            board.tileAt(1, 2),
                            board.tileAt(2, 2),
                            board.tileAt(2, 1)};
                return sample;
            }
            case 13: {
                SamplePointValue[] sample
                        = {board.tileAt(2, 1),
                            board.tileAt(2, 2),
                            board.tileAt(3, 2),
                            board.tileAt(3, 1)};
                return sample;
            }
            //tercera fila de rectangulos
            case 14: {
                SamplePointValue[] sample
                        = {board.tileAt(0, 2),
                            board.tileAt(0, 3),
                            board.tileAt(1, 3),
                            board.tileAt(1, 2)};
                return sample;
            }
            case 15: {
                SamplePointValue[] sample
                        = {board.tileAt(1, 2),
                            board.tileAt(1, 3),
                            board.tileAt(2, 3),
                            board.tileAt(2, 2)};
                return sample;
            }
            case 16: {
                SamplePointValue[] sample
                        = {board.tileAt(2, 2),
                            board.tileAt(2, 3),
                            board.tileAt(3, 3),
                            board.tileAt(3, 2)};
                return sample;
            }

            default: {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

    @Override
    public double normalizeValueToPerceptronOutput(Object value) {
        if ( (Double) value > maxReward ) {
            throw new IllegalArgumentException(
                    "value no puede ser mayor a maxReward=" + maxReward);
        }
        return normOutput.normalize((Double) value);
    }
}