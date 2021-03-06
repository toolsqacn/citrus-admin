/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bar;

import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.annotations.CitrusXmlTest;
import org.testng.annotations.Test;

/**
 * @author Christoph Deppisch
 */
@Test
public class BarTest extends TestNGCitrusTestDesigner {

    @CitrusXmlTest(name = "BarTest")
    public void barTest() {
    }

    @CitrusXmlTest(name = { "Bar2Test" })
    public void bar2Test() {
    }

    @CitrusXmlTest(name="BarPackageTest", packageName = "com.consol.citrus.bar")
    public void barPackageTest() {
    }

    @CitrusXmlTest(packageName = "com.consol.citrus.bar")
    public void barPackageNameTest() {
    }

    @CitrusXmlTest(packageScan = "com.consol.citrus.bar.scan")
    public void barPackageScanTest() {
    }
}