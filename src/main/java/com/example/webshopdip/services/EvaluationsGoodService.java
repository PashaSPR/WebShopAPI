package com.example.webshopdip.services;

import com.example.webshopdip.dtos.EvaluationsGoodDTO;
import com.example.webshopdip.entities.EvaluationsGoodEntity;
import com.example.webshopdip.exceptions.CustomersNotFoundException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.repositories.EvaluationsGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationsGoodService {

    @Autowired
    private EvaluationsGoodRepository evaluationsGoodRepository;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    public List<EvaluationsGoodEntity> getAllEvaluations() {
        return evaluationsGoodRepository.findAll();
    }

    public EvaluationsGoodEntity createEvaluationsGoodFromDTO(EvaluationsGoodDTO evaluationsGoodDTO) throws CustomersNotFoundException, GoodsNotFoundException {
        EvaluationsGoodEntity evaluationsGood = new EvaluationsGoodEntity();
        System.out.println("service createEvaluationsGoodFromDTO");
        evaluationsGood.setCustomers(customersService.getOne(evaluationsGoodDTO.getCustomerId()));
        System.out.println("setCustomers");

        evaluationsGood.setGoods(goodsService.getGoodsById(evaluationsGoodDTO.getGoodsId()));
        System.out.println("setGoods");

        evaluationsGood.setEvaluation(evaluationsGoodDTO.getEvaluation());
        System.out.println("setEvaluation");

        return evaluationsGoodRepository.save(evaluationsGood);
    }

    public Integer evaluationsByGood (Long id)
    {
        List<EvaluationsGoodEntity> evaluationsGoodEntities = evaluationsGoodRepository.findByGoodsId(id);
        Integer summ = 0, num = 0, evaluation = 0;
        for (EvaluationsGoodEntity evaluationsGood : evaluationsGoodEntities) {
            summ += evaluationsGood.getEvaluation();
            num++;
        }
        if (summ.equals(0) || num.equals(0))
        {
            evaluation=0;
        }
        else evaluation=summ/num;

        return evaluation;
    }


}