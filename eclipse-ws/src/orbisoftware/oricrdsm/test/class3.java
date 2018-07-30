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

public class class3 {

   public float field1 = 321.4f;
   public byte field2 = 9;
   public int[] field3 = { 1, 2, 3, 4, 5 };
   protected long field4 = 59;
   private class4 field5 = new class4(2);
   public class3() {
      field5 = class4.from_int(1);
      field2 = (byte)field5.value();
   }
}
