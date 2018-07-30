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

public class class1 {

   @SuppressWarnings("unused")
   private String field1[] = new String[] { "a", "b", "c", "d" };
   protected class2 field2[] = new class2[6];
   @SuppressWarnings("unused")
   private int field3 = 52;
   public short field4 = 95;
   class3 field5 = new class3();

   public class1() {
      field2[0] = new class2();
      field2[1] = new class2();
      field2[2] = new class2();
      field2[3] = new class2();
      field2[4] = new class2();
      field2[5] = new class2();
   }
}
