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
package ar;

import ar.edu.EduSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
@RunWith( Suite.class )
@SuiteClasses( EduSuite.class )
public
class ArSuite {

    /**
     * @throws Exception
     */
    @BeforeClass
    public static
    void setUpClass()
            throws Exception {
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static
    void tearDownClass()
            throws Exception {
    }

    /**
     * @throws Exception
     */
    @Before
    public
    void setUp()
            throws Exception {
    }

    /**
     * @throws Exception
     */
    @After
    public
    void tearDown()
            throws Exception {
    }

}
