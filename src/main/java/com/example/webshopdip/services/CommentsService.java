//package com.example.webshopdip.services;
//
//import com.example.webshopdip.dtos.CommentsDTO;
//import com.example.webshopdip.dtos.CommentsWithGoodDTO;
//import com.example.webshopdip.dtos.GoodsGetAllDTO;
//import com.example.webshopdip.entities.CommentsEntity;
//import com.example.webshopdip.entities.GoodsEntity;
//import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
//import com.example.webshopdip.exceptions.CommentsNotFoundException;
//import com.example.webshopdip.exceptions.GoodsNotFoundException;
//import com.example.webshopdip.exceptions.SubcategoriesGoodsNotFoundException;
//import com.example.webshopdip.exceptions.UsersListsNotFoundException;
//import com.example.webshopdip.repositories.CommentsRepository;
//import com.example.webshopdip.repositories.GoodsRepository;
//import com.example.webshopdip.repositories.UsersListsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//@Service
//public class CommentsService {
//    @Autowired
//    private CommentsRepository commentsRepository;
//    @Autowired
//    private GoodsRepository goodsRepository;
//
//    @Autowired
//    private UsersListsService usersListsService;
//    @Autowired
//    private UsersListsRepository usersListsRepository;
//
//    @Autowired
//    private GoodsService goodsService;
//
//    @Autowired
//    public List<CommentsEntity> getAllComments() {
//        return commentsRepository.findAll(Sort.by(Sort.Order.asc("id")));
//    }
//
//
//    public CommentsEntity createCommentFromDTO(CommentsDTO commentsDTO) throws UsersListsNotFoundException, GoodsNotFoundException {
//        CommentsEntity comment = new CommentsEntity();
//        comment.setDate_comment(LocalDate.now());
//        comment.setUsersLists(usersListsRepository.getOne(commentsDTO.getUsersListsId()));
//        System.out.println("UserListsId " + commentsDTO.getUsersListsId());
//        comment.setGoods(goodsService.getGoodsById(commentsDTO.getGoodsId()) );
//        System.out.println("GoodsId " + commentsDTO.getGoodsId());
//        comment.setComment(commentsDTO.getComment());
//
//        return commentsRepository.save(comment);
//    }
//
//
//
////    public List<CommentsEntity> getCommentsByGoodsId(Long goodsId) {
////        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
////        return comments;
////    }
//
//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment: comments){
////            System.out.println("comment.id: " + comment.getId());
//            CommentsWithGoodDTO commentsDTO =new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//            commentsDTOS.add(commentsDTO);
//        }
//        return commentsDTOS;
//    }
//
//    public CommentsEntity getOne(Long id) throws CommentsNotFoundException {
//        Optional<CommentsEntity> optional = commentsRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new CommentsNotFoundException("Коментаря не знайдено");
//        }
//        return optional.get();
//    }
//}
//////////////////////////////////////////////////////////////
package com.example.webshopdip.services;

import com.example.webshopdip.dtos.CommentsAndEvaluationDTO;
import com.example.webshopdip.dtos.CommentsDTO;
import com.example.webshopdip.dtos.CommentsWithGoodDTO;
import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.CommentsEntity;
import com.example.webshopdip.entities.EvaluationsGoodEntity;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.CommentsNotFoundException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.exceptions.SubcategoriesGoodsNotFoundException;
import com.example.webshopdip.exceptions.UsersListsNotFoundException;
import com.example.webshopdip.repositories.CommentsRepository;
import com.example.webshopdip.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.*;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UsersListsService usersListsService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private  EvaluationsGoodService evaluationsGoodService;

    @Autowired
    public List<CommentsEntity> getAllComments() {
        return commentsRepository.findAll();
    }


    public CommentsEntity createCommentFromDTO(CommentsDTO commentsDTO) throws UsersListsNotFoundException, GoodsNotFoundException {
        CommentsEntity comment = new CommentsEntity();
        comment.setDate_comment(LocalDate.now());
        comment.setUsersLists(usersListsService.getOne(commentsDTO.getUsersListsId()));
        System.out.println("UserListsId " + commentsDTO.getUsersListsId());
        comment.setGoods(goodsService.getGoodsById(commentsDTO.getGoodsId()) );
        System.out.println("GoodsId " + commentsDTO.getGoodsId());
        comment.setComment(commentsDTO.getComment());

        return commentsRepository.save(comment);
    }



//    public List<CommentsEntity> getCommentsByGoodsId(Long goodsId) {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//        return comments;
//    }

//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment: comments){
////            System.out.println("comment.id: " + comment.getId());
//            CommentsWithGoodDTO commentsDTO =new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//            List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
//            for(EvaluationsGoodEntity evaluation: listEvaluations)
//            {
//               System.out.println("Evaluation Good Id  " + evaluation.getGoods().getId());
//               System.out.println("Optional Good Id " + goodsId);
//                if(evaluation.getGoods().getId().equals(goodsId))
//                {
//                    System.out.println("Evaluation Customer Id  " + evaluation.getCustomers().getUsersLists().getId());
//                    System.out.println("Optional Customer Id " + commentsDTO.getUsersListsId().getId());
//                    if(evaluation.getCustomers().getUsersLists().getId().equals(commentsDTO.getUsersListsId().getId()))
//                    {
//                        commentsDTO.setEvaluation(evaluation.getEvaluation());
//                    }
//                }
////            if(evaluation.getGoods().getId().equals(goodsId) ||
////                    evaluation.getCustomers().getUsersLists().getId()
////                            .equals(commentsDTO.getUsersListsId())){
////
////                commentsDTO.setEvaluation(evaluation.getEvaluation());
////            }
//            }
//            commentsDTOS.add(commentsDTO);
//        }
//
//        return commentsDTOS;
//    }

//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//
//        Map<Long, Integer> userRatings = new HashMap<>(); // Мапа для збереження кількості зірочок для кожного користувача
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment : comments) {
//            CommentsWithGoodDTO commentsDTO = new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//
//            List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
//            for (EvaluationsGoodEntity evaluation : listEvaluations) {
//                if (evaluation.getGoods().getId().equals(goodsId) && evaluation.getCustomers().getUsersLists().getId().equals(commentsDTO.getUsersListsId().getId())) {
//                    commentsDTO.setEvaluation(evaluation.getEvaluation());
//
//                    // Оновлюємо кількість зірочок для користувача в мапі
//                    userRatings.put(commentsDTO.getUsersListsId().getId(), evaluation.getEvaluation());
//                    break; // Можна вийти з циклу, оскільки зірочки знайдені
//                }
//            }
//
//            commentsDTOS.add(commentsDTO);
//        }
//
//        // Додаємо кількість зірочок для кожного користувача до відповідного коментаря
//        for (CommentsWithGoodDTO commentsDTO : commentsDTOS) {
//            Integer userRating = userRatings.get(commentsDTO.getUsersListsId().getId());
//            commentsDTO.setUsersListsId(userRating != null ? userRating : 0); // Якщо користувач не поставив зірочки, встановлюємо 0
//        }
//
//        return commentsDTOS;
//    }

//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment : comments) {
//            CommentsWithGoodDTO commentsDTO = new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//
//            List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
//            for (EvaluationsGoodEntity evaluation : listEvaluations) {
//                if (evaluation.getGoods().getId().equals(goodsId) && evaluation.getCustomers().getUsersLists().getId().equals(commentsDTO.getUsersListsId().getId())) {
//                    commentsDTO.setEvaluation(evaluation.getEvaluation());
//                    break; // Можна вийти з циклу, оскільки зірочки знайдені
//                }
//            }
//
//            commentsDTOS.add(commentsDTO);
//        }
//
//        return commentsDTOS;
//    }

//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//        List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment : comments) {
//            CommentsWithGoodDTO commentsDTO = new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//
//            int userRating = 0; // Змінна для збереження кількості зірочок користувача
//
//            for (EvaluationsGoodEntity evaluation : listEvaluations) {
//                if (evaluation.getGoods().getId().equals(goodsId) && evaluation.getCustomers().getUsersLists().getId().equals(commentsDTO.getUsersListsId().getId())) {
//                    userRating = evaluation.getEvaluation();
//                    break; // Можна вийти з циклу, оскільки зірочки знайдені
//                }
//            }
//
//            commentsDTO.setEvaluation(userRating);
//            commentsDTOS.add(commentsDTO);
//        }
//
//        return commentsDTOS;
//    }

//    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//        List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
//
//        // Мапа для збереження оцінок кожного користувача
//        Map<Long, Integer> userRatings = new HashMap<>();
//        for (EvaluationsGoodEntity evaluation : listEvaluations) {
//            if (evaluation.getGoods().getId().equals(goodsId)) {
//                userRatings.put(evaluation.getCustomers().getUsersLists().getId(), evaluation.getEvaluation());
//            }
//        }
//
//        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
//        for (CommentsEntity comment : comments) {
//            CommentsWithGoodDTO commentsDTO = new CommentsWithGoodDTO();
//            commentsDTO.setId(comment.getId());
//            commentsDTO.setComment(comment.getComment());
//            commentsDTO.setDate_comment(comment.getDate_comment());
//            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
//            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
//
//            // Отримуємо оцінку для відповідного користувача і встановлюємо її для коментаря
//            Integer userRating = userRatings.get(commentsDTO.getUsersListsId().getId());
//            commentsDTO.setEvaluation(userRating != null ? userRating : 0);
//
//            commentsDTOS.add(commentsDTO);
//        }
//
//        return commentsDTOS;
//    }

    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);

        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();

        for (CommentsEntity comment : comments) {
            CommentsWithGoodDTO commentsDTO = new CommentsWithGoodDTO();
            commentsDTO.setId(comment.getId());
            commentsDTO.setComment(comment.getComment());
            commentsDTO.setDate_comment(comment.getDate_comment());
            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));

            List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();
            boolean userRatingFound = false;

            for (EvaluationsGoodEntity evaluation : listEvaluations) {
                if (evaluation.getGoods().getId().equals(goodsId) && evaluation.getCustomers().getUsersLists().getId().equals(commentsDTO.getUsersListsId().getId())) {
                    commentsDTO.setEvaluation(evaluation.getEvaluation());
                    userRatingFound = true;
                    break;
                }
            }

            // Якщо оцінка не знайдена, встановлюємо 0
            if (!userRatingFound) {
                commentsDTO.setEvaluation(0);
            }

            commentsDTOS.add(commentsDTO);
        }

        return commentsDTOS;
    }

    public CommentsAndEvaluationDTO getOne(Long id) throws CommentsNotFoundException {
        Optional<CommentsEntity> optional = commentsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CommentsNotFoundException("Коментаря не знайдено");
        }
        CommentsAndEvaluationDTO commentsAndEvaluationDTO = new CommentsAndEvaluationDTO();
        commentsAndEvaluationDTO.setId(optional.get().getId());
        commentsAndEvaluationDTO.setDate_comment(optional.get().getDate_comment());
        commentsAndEvaluationDTO.setComment(optional.get().getComment());
        commentsAndEvaluationDTO.setGoodsId(optional.get().getGoods().getId());
        commentsAndEvaluationDTO.setUsersListsId(optional.get().getUsersLists().getId());
        List<EvaluationsGoodEntity> listEvaluations = evaluationsGoodService.getAllEvaluations();

        for(EvaluationsGoodEntity evaluation: listEvaluations)
        {
            System.out.println("Evaluation Good Id  " + evaluation.getGoods().getId());
            System.out.println("Optional Good Id " + optional.get().getGoods().getId());
            if(evaluation.getGoods().getId().equals(optional.get().getGoods().getId()))
            {
                System.out.println("Evaluation Customer Id  " + evaluation.getCustomers().getUsersLists().getId());
                System.out.println("Optional Customer Id " + optional.get().getUsersLists().getId());
                if(evaluation.getCustomers().getUsersLists().getId().equals(optional.get().getUsersLists().getId()))
                {
                    commentsAndEvaluationDTO.setEvaluation(evaluation.getEvaluation());
                }
            }
//            if(evaluation.getGoods().getId().equals(optional.get().getGoods().getId()) || evaluation.getCustomers().getUsersLists().getId().equals(optional.get().getUsersLists().getId())){
//                commentsAndEvaluationDTO.setEvaluation(evaluation.getEvaluation());
//            }
        }
        return commentsAndEvaluationDTO;
    }
}