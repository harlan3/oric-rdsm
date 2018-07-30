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

import static org.junit.Assert.assertEquals;
import com.google.gson.Gson;

import orbisoftware.oricrdsm.OricSymbolDict;
import orbisoftware.oricrdsm.OricSymbolMap;

public class TestCases {

   public static void main(String[] args) {

      TestCases testCases = new TestCases();
      testCases.runTestCases(false, true, true);
   }

   private void runTestCases(boolean dumpSymbolMap, boolean showProfiling,
         boolean apiTest) {

      // Primitives
      runPrimitiveTests(dumpSymbolMap, showProfiling);

      // Array of Primitives
      runArrayOfPrimitivesTests(dumpSymbolMap, showProfiling);

      // Object
      runObjectTests(dumpSymbolMap, showProfiling);

      // Test API
      if (apiTest)
         runAPITests();
   }

   private void runAPITests() {

      OricSymbolMap test1SymMap = new OricSymbolMap();
      class1 testObj1 = new class1();

      test1SymMap.objToSymMap(testObj1);

      String baseSymbol = test1SymMap.getBaseSymbol();
      String symbol = OricSymbolDict.SYM_PREFIX + 206;
      System.out.println("symbol count = " + test1SymMap.getSymbolCount());
      System.out.println("base symbol = " + baseSymbol);
      System.out.println("child count = "
            + test1SymMap.getChildCount(baseSymbol));
      for (String child : test1SymMap.getChildren(baseSymbol))
         System.out.println("  child = " + child);
      System.out.println("child by index = "
            + test1SymMap.getChildByIndex(baseSymbol, 2));
      System.out.println("child index = "
            + test1SymMap.getIndexOfChild(baseSymbol,
                  test1SymMap.getChildByIndex(baseSymbol, 2)));
      System.out.println("symbol type = " + test1SymMap.getSymbolType(symbol));
      System.out.println("symbol parent = " + test1SymMap.getParent(symbol));
      System.out.println("is leaf = " + test1SymMap.isLeaf(symbol));
      System.out.println("field name = " + test1SymMap.getFieldName(symbol));
      System.out.println("field type = " + test1SymMap.getFieldType(symbol));
      System.out.println("field value = " + test1SymMap.getFieldValue(symbol));
      test1SymMap.setFieldValue(symbol, Integer.toString(10));
      System.out.println("field value = " + test1SymMap.getFieldValue(symbol));

   }

   private void runPrimitiveTests(boolean dumpTables, boolean showProfiling) {

      long start, end;
      Gson gson = new Gson();

      try {

         // Test case: boolean
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            boolean testObj1 = true;
            boolean testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Boolean) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Boolean.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: integer
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            int testObj1 = 142857;
            int testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Integer) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Integer.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: character
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            char testObj1 = 'Q';
            char testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Character) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Character.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: byte
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            byte testObj1 = 36;
            byte testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Byte) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Byte.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: short
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            short testObj1 = 10346;
            short testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Short) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Short.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: double
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            double testObj1 = 3.141592653589793;
            double testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Double) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Double.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: long
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            long testObj1 = 73939133;
            long testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Long) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Long.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: float
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            float testObj1 = 2.718281f;
            float testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (Float) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, Float.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: string
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            String testObj1 = "Oric RDSM";
            String testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (String) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, String.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

      } catch (Exception e) {
         System.out.println(e);
      }
   }

   private void runArrayOfPrimitivesTests(boolean dumpTables,
         boolean showProfiling) {

      long start, end;
      Gson gson = new Gson();

      try {

         // Test case: boolean
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            boolean[] testObj1 = new boolean[] { true, false, false, true };
            boolean[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (boolean[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, boolean[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: integer
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            int[] testObj1 = new int[] { 142857, 42548, 94242, 94952, 49522 };
            int[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (int[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, int[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: character
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            char[] testObj1 = new char[] { 'O', 'R', 'B', 'I', 'S' };
            char[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (char[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, char[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: byte
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            byte[] testObj1 = new byte[] { 1, 2, 3, 4, 5 };
            byte[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (byte[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, byte[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: short
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            short[] testObj1 = { 3241, 9592, 5821, 4965, 1283 };
            short[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (short[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, short[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: double
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            double[] testObj1 = { 43.2346, 95.2342, 48.958, 82.992, 44.439 };
            double[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (double[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, double[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: long
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            long[] testObj1 = { 4521423, 942532, 949422, 2662854, 746622 };
            long[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (long[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, long[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: float
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            float[] testObj1 = { 3546.7131f, 73245.8281f, 9874.3681f,
                  4591.4281f, 9413.8281f };
            float[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (float[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, float[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

         // Test case: string
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            String[] testObj1 = { "Orbis", "Software", "-", "Oric", "RDSM" };
            String[] testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (String[]) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, String[].class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

      } catch (Exception e) {
         System.out.println(e);
      }
   }

   private void runObjectTests(boolean dumpTables, boolean showProfiling) {

      long start, end;
      Gson gson = new Gson();

      try {

         // Test case: Object
         {
            StringBuffer buffer = new StringBuffer();
            OricSymbolMap test1SymMap = new OricSymbolMap();
            OricSymbolMap test2SymMap = new OricSymbolMap();
            class1 testObj1 = new class1();
            class1 testObj2;
            String testObj1Json;
            String testObj2Json;

            testObj1Json = gson.toJson(testObj1);

            // OricSymbolMap.objToSymMap
            start = System.nanoTime();
            test1SymMap.objToSymMap(testObj1);
            end = System.nanoTime();
            if (dumpTables)
               test1SymMap.dumpSymbolMap();
            if (showProfiling)
               System.out.println("\nOricSymbolMap.objToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.symMapToObj
            start = System.nanoTime();
            testObj2 = (class1) test1SymMap.symMapToObj();
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.symMapToObj (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.objToSymMapBuffer
            start = System.nanoTime();
            test1SymMap.objToSymMapBuffer(testObj2, buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.objToSymMapBuffer (ms): "
                     + Long.toString((end - start) / 1000000));

            // OricSymbolMap.bufferToSymMap
            start = System.nanoTime();
            test2SymMap.bufferToSymMap(buffer);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("OricSymbolMap.bufferToSymMap (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.toJson
            start = System.nanoTime();
            testObj2Json = gson.toJson(testObj2);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.toJson (ms): "
                     + Long.toString((end - start) / 1000000));

            // gson.fromJson
            start = System.nanoTime();
            testObj2 = gson.fromJson(testObj2Json, class1.class);
            end = System.nanoTime();
            if (showProfiling)
               System.out.println("Gson.fromJson (ms): "
                     + Long.toString((end - start) / 1000000));
            if ((dumpTables) || (showProfiling))
               System.out.println();

            assertEquals(testObj1Json, testObj2Json);
         }

      } catch (Exception e) {
         System.out.println(e);
      }
   }
}
