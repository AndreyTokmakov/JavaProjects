/*
 * Copyright (C) 2020 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Dagger;

import dagger.Component;
import dagger.Lazy;

import javax.inject.Inject;
import javax.inject.Singleton;

/** The main app responsible for brewing the coffee and printing the logs. */
public class CoffeeApp {
    @Singleton
    @Component(
            modules = {
                    HeaterModule.class,
                    PumpModule.class
            }
    )
    public interface CoffeeShop {
        CoffeeMaker maker();
        CoffeeLogger logger();
    }


    /*
    static class CoffeeShopEx implements CoffeeShop {
        CoffeeMaker maker;
        CoffeeLogger logger;

        @Inject
        CoffeeShopEx(CoffeeMaker maker, CoffeeLogger logger) {
            this.maker = maker;
            this.logger = logger;
        }
        @Override
        public CoffeeMaker maker() {
            return maker;
        }

        @Override
        public CoffeeLogger logger() {
            return logger;
        }
    }
    */

    public static void main(String[] args)
    {
        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        coffeeShop.maker().brew();
        coffeeShop.logger().logs().forEach(System.out::println);
    }
}