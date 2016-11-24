#!/bin/bash
java -cp "generatorBuilder/modules/*;generatorBuilder/lib/*" com.bbva.kltt.controller.serverSpring.APIRestGeneratorLauncher config/config.properties &
