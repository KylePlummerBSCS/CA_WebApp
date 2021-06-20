package com.revature.WebApp.AllSamples;

import com.revature.WebApp.AllSamples.entities.Eggs;
import com.revature.WebApp.AllSamples.entities.Baskets;
import com.revature.WebApp.AllSamples.repositories.EggsRepository;
import com.revature.WebApp.AllSamples.repositories.BasketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestClass {
    public BasketsRepository basketRepo;
    public EggsRepository eggRepo;

    @Autowired
    public TestClass(BasketsRepository basketRepo, EggsRepository eggRepo) {
        this.basketRepo = basketRepo;
        this.eggRepo = eggRepo;
        Baskets basket = new Baskets();
        basketRepo.save(basket);
        Integer basketId = basket.getId();

        Eggs egg1 = new Eggs();
        egg1.setBasket(basket);
        Eggs egg2 = new Eggs();
        egg2.setBasket(basket);
        eggRepo.save(egg1);
        eggRepo.save(egg2);
    }

}
