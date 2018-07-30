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

public class class2 {

   public double field1 = 863.4;
   public byte field2 = 3;
   public int[] field3 = { 14, 21, 55, 444, 555 };
   @SuppressWarnings("unused")
   private class3 field4;
   private class3 field5[] = new class3[3];

   public class2() {
      field4 = new class3();
      field5[0] = new class3();
      field5[1] = new class3();
      field5[2] = new class3();
   }

}
