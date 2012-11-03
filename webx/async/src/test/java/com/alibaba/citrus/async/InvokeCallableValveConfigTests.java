/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.citrus.async;

import static org.junit.Assert.*;

import com.alibaba.citrus.async.pipeline.valve.InvokeCallableValve;
import com.alibaba.citrus.async.pipeline.valve.StartAsyncValve;
import com.alibaba.citrus.service.pipeline.impl.PipelineImpl;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvokeCallableValveConfigTests extends AbstractAsyncTests {
    private StartAsyncValve     startAsyncValve;
    private InvokeCallableValve valve;
    private PipelineImpl        pipeline;

    @BeforeClass
    public static void initClass() {
        defaultFactory = createApplicationContext("invokeCallableValveConfig.xml");
    }

    @Test
    public void subPipelineNotSpecified() {
        startAsyncValve = getValve("pipeline1", 0, StartAsyncValve.class);
        valve = getInvokeCallableValve(0);

        assertEquals("screenResult", valve.getResultName());
    }

    @Test
    public void resultNameNotSpecified() {
        startAsyncValve = getValve("pipeline2", 0, StartAsyncValve.class);
        valve = getInvokeCallableValve(1);

        assertEquals("screenResult", valve.getResultName());
    }

    @Test
    public void resultNameSpecified() {
        startAsyncValve = getValve("pipeline3", 0, StartAsyncValve.class);
        valve = getInvokeCallableValve(1);

        assertEquals("myresult", valve.getResultName());
    }

    private InvokeCallableValve getInvokeCallableValve(int index) {
        PipelineImpl asyncPipeline = (PipelineImpl) startAsyncValve.getAsyncPipeline();
        return (InvokeCallableValve) asyncPipeline.getValves()[index];
    }
}