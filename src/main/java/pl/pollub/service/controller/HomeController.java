/*
 * Copyright 2015 Benedikt Ritter
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
package pl.pollub.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pollub.service.DatabaseSaver;
import pl.pollub.service.repository.PlayerRepository;

@Controller
public class HomeController {

    @Autowired
    private DatabaseSaver databaseSaver;

    @Autowired
    private PlayerRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/destroy", method = RequestMethod.GET)
    public String destroy() {
        repository.deleteAll();
        return "redirect:/";
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        databaseSaver.init();
        return "redirect:/";
    }
}
