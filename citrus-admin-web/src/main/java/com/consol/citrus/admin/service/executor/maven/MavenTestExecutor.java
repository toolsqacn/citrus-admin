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

package com.consol.citrus.admin.service.executor.maven;

import com.consol.citrus.admin.process.*;
import com.consol.citrus.admin.process.listener.LoggingProcessListener;
import com.consol.citrus.admin.process.listener.WebSocketProcessListener;
import com.consol.citrus.admin.service.ProjectService;
import com.consol.citrus.admin.service.executor.TestExecutor;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Christoph Deppisch
 */
@Component
public class MavenTestExecutor implements TestExecutor {

    @Autowired
    private WebSocketProcessListener webSocketProcessListener;

    @Autowired
    private ProcessMonitor processMonitor;
    @Autowired
    private ProjectService projectService;

    @Override
    public String execute(String packageName, String testName) throws ParseException {
        File projectHome = new File(projectService.getActiveProject().getProjectHome());
        ProcessBuilder processBuilder = new MavenRunTestsCommand(projectHome, testName).getProcessBuilder();
        ProcessLauncher processLauncher = new ProcessLauncherImpl(processMonitor, testName);

        processLauncher.addProcessListener(webSocketProcessListener);
        processLauncher.addProcessListener(new LoggingProcessListener());
        processLauncher.launchAndContinue(processBuilder, 0);

        return processLauncher.getProcessId();
    }
}