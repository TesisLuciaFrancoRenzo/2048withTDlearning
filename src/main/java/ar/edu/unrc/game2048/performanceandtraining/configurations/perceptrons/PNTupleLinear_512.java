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
package ar.edu.unrc.game2048.performanceandtraining.configurations.perceptrons;

import ar.edu.unrc.coeus.tdlearning.training.ntuple.NTupleSystem;
import ar.edu.unrc.coeus.tdlearning.training.ntuple.SamplePointValue;
import ar.edu.unrc.game2048.Game2048;
import ar.edu.unrc.game2048.GameBoard;
import ar.edu.unrc.game2048.NeuralNetworkConfiguration2048;
import ar.edu.unrc.game2048.Tile;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationLinear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Configuración para jugar hasta 512, tablero de tipo NTupla, con función de activación Tangente Hiperbólica, y puntaje
 * parcial.
 *
 * @param <NeuralNetworkClass>
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class PNTupleLinear_512<NeuralNetworkClass>
        extends NeuralNetworkConfiguration2048<NeuralNetworkClass> {

    private final List<SamplePointValue>             allSamplePointPossibleValues;
    private final boolean                            concurrentInput;
    private final HashMap<SamplePointValue, Integer> mapSamplePointValuesIndex;
    private final int                                maxTile;
    private final int[]                              nTuplesLength;
    private final int[]                              nTuplesWeightQuantityIndex;
    private final int                                numSamples;
    private final boolean                            useNTupleList;

    /**
     * @param hasBias
     */
    public
    PNTupleLinear_512(final Boolean hasBias) {
        this.hasBias = hasBias;

        numSamples = 8;
        maxTile = 9;
        concurrentInput = true;
        useNTupleList = true;

        nTuplesLength = new int[numSamples];
        for (int i = 0; i < numSamples; i++) {
            nTuplesLength[i] = 4;
        }

        this.allSamplePointPossibleValues = new ArrayList<>();
        this.mapSamplePointValuesIndex = new HashMap<>();
        for (int spvIndex = 0; spvIndex <= maxTile; spvIndex++) {
            allSamplePointPossibleValues.add(new Tile(spvIndex));
            mapSamplePointValuesIndex.put(allSamplePointPossibleValues.get(spvIndex), spvIndex);
        }

        int lutSize                   = 0;
        int lastNTuplesWeightQuantity = 0;
        nTuplesWeightQuantityIndex = new int[nTuplesLength.length];
        nTuplesWeightQuantityIndex[0] = lastNTuplesWeightQuantity;

        for (int nTupleIndex = 0; nTupleIndex < nTuplesLength.length; nTupleIndex++) {
            int nTuplesWeightQuantity = (int) Math.pow(mapSamplePointValuesIndex.size(), nTuplesLength[nTupleIndex]);
            lutSize += nTuplesWeightQuantity;
            if (nTupleIndex > 0) {
                nTuplesWeightQuantityIndex[nTupleIndex] = nTuplesWeightQuantityIndex[nTupleIndex - 1] + lastNTuplesWeightQuantity;
            }
            lastNTuplesWeightQuantity = nTuplesWeightQuantity;
        }
        this.neuronQuantityInLayer = new int[2];
        neuronQuantityInLayer[1] = 1;
        neuronQuantityInLayer[0] = lutSize;

        this.activationFunctionForEncog = new ActivationFunction[1];
        activationFunctionForEncog[0] = new ActivationLinear();

        //        activationFunctionMax = 1;
        //        activationFunctionMin = -1;
    }

    /**
     * @param nTupleIndex
     * @param nTuple
     *
     * @return
     */
    public
    int calculateIndex(
            int nTupleIndex,
            SamplePointValue[] nTuple
    ) {
        return nTuplesWeightQuantityIndex[nTupleIndex] +
               NTupleSystem.calculateLocalIndex(nTupleIndex, nTuplesLength, nTuple, mapSamplePointValuesIndex);
    }

    @Override
    public
    void calculateNormalizedPerceptronInput(
            GameBoard<NeuralNetworkClass> board,
            List<Double> normalizedPerceptronInput
    ) {
        for (int i = 0; i < numSamples; i++) {
            normalizedPerceptronInput.add(calculateIndex(i, getNTuple(board, i)), 1d);
        }
    }

    /**
     * @return @throws CloneNotSupportedException
     */
    @Override
    public
    Object clone()
            throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Double computeNumericRepresentationFor(
            Game2048 game,
            Object[] output
    ) {
        return (Double) output[0];
    }

    @Override
    public
    double deNormalizeValueFromNeuralNetworkOutput(Object value) {
        return (Double) value;
    }

    /**
     * @return allSamplePointPossibleValues
     */
    public
    List<SamplePointValue> getAllSamplePointPossibleValues() {
        return allSamplePointPossibleValues;
    }

    @Override
    public
    double getBoardReward(
            GameBoard board,
            int outputNeuron
    ) {
        return board.getPartialScore();
    }

    @Override
    public
    double getFinalReward(
            GameBoard board,
            int outputNeuron
    ) {
        return board.getGame().getScore();
    }

    /**
     * @return mapSamplePointValuesIndex
     */
    public
    HashMap<SamplePointValue, Integer> getMapSamplePointValuesIndex() {
        return mapSamplePointValuesIndex;
    }

    /**
     * @return maxTile
     */
    public
    int getMaxTile() {
        return maxTile;
    }

    /**
     * @param board
     * @param nTupleIndex
     *
     * @return
     */
    public
    SamplePointValue[] getNTuple(
            GameBoard board,
            int nTupleIndex
    ) {
        switch (nTupleIndex) {
            // verticales
            case 0: {
                return new SamplePointValue[]{board.tileAt(0, 0), board.tileAt(0, 1), board.tileAt(0, 2), board.tileAt(0, 3)};
            }
            case 1: {
                return new SamplePointValue[]{board.tileAt(1, 0), board.tileAt(1, 1), board.tileAt(1, 2), board.tileAt(1, 3)};
            }
            case 2: {
                return new SamplePointValue[]{board.tileAt(2, 0), board.tileAt(2, 1), board.tileAt(2, 2), board.tileAt(2, 3)};
            }
            case 3: {
                return new SamplePointValue[]{board.tileAt(3, 0), board.tileAt(3, 1), board.tileAt(3, 2), board.tileAt(3, 3)};
            }
            // horizontales
            case 4: {
                return new SamplePointValue[]{board.tileAt(0, 0), board.tileAt(1, 0), board.tileAt(2, 0), board.tileAt(3, 0)};
            }
            case 5: {
                return new SamplePointValue[]{board.tileAt(0, 1), board.tileAt(1, 1), board.tileAt(2, 1), board.tileAt(3, 1)};
            }
            case 6: {
                return new SamplePointValue[]{board.tileAt(0, 2), board.tileAt(1, 2), board.tileAt(2, 2), board.tileAt(3, 2)};
            }
            case 7: {
                return new SamplePointValue[]{board.tileAt(0, 3), board.tileAt(1, 3), board.tileAt(2, 3), board.tileAt(3, 3)};
            }
            default: {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

    /**
     * @return longitud de las nTuplas.
     */
    public
    int[] getNTuplesLength() {
        return nTuplesLength;
    }

    /**
     * @return nTuplesWeightQuantityIndex
     */
    public
    int[] getNTuplesWeightQuantityIndex() {
        return nTuplesWeightQuantityIndex;
    }

    /**
     * @return numero de muestras.
     */
    public
    int getNumSamples() {
        return numSamples;
    }

    @Override
    public
    boolean isConcurrentInputEnabled() {
        return concurrentInput;
    }

    @Override
    public
    double normalizeValueToPerceptronOutput(Object value) {
        return (Double) value;
    }

    @Override
    public
    boolean useNTupleList() {
        return useNTupleList;
    }
}
