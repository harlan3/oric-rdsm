/*
 *  Oric RDSM provides java object persistence and introspection
 *  through the use of a Recursive Descent Symbol Map.
 *
 *  Copyright (C) 2011 Harlan Murphy
 *  Orbis Software - orbisoftware@gmail.com
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License Version 3 or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License 
 *  along with this software.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */

package orbisoftware.oricrdsm.test;

public class class4 {

    private int __value;
    private static int __size = 4;
    private static orbisoftware.oricrdsm.test.class4[] __array = new orbisoftware.oricrdsm.test.class4[__size];

    public static final int _state1 = 0;
    public static final orbisoftware.oricrdsm.test.class4 state1 = new orbisoftware.oricrdsm.test.class4(_state1);

    public static final int _state2 = 1;
    public static final orbisoftware.oricrdsm.test.class4 state2 = new orbisoftware.oricrdsm.test.class4(_state2);

    public int value ()
    {
        return __value;
    }

    public static orbisoftware.oricrdsm.test.class4 from_int (int value)
    {
        return __array[value];
    }

    protected class4 (int value)
    {
        __value = value;
        __array[__value] = this;
    }
}
