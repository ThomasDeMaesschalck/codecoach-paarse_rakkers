package com.switchfully.codecoach.repository.specifications;

import com.switchfully.codecoach.domain.CoachInfo;
import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.UserRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class UserSpecification {

    public Specification<User> getUsers(Topic.TopicName topicName, UserRole userRole, String partialSearch) {
        return (user, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (topicName != null) {
                Join<CriteriaBuilder.Case<User>, CoachInfo> coachInfoJoin = user.join("coachInfo");
                Join<CriteriaBuilder.Case<User>, Topic> topicJoin = coachInfoJoin.join("topics");

                predicates.add(criteriaBuilder.equal(topicJoin.get("topicname"), topicName));
            }

            if (userRole != null) {
                if (userRole.equals(UserRole.COACH)) {
                    predicates.add(criteriaBuilder.notEqual(user.get("userRole"), UserRole.COACHEE));
                } else {
                    predicates.add(criteriaBuilder.equal(user.get("userRole"), userRole));
                }
            }

            if (partialSearch != null) {

                Predicate firstName = criteriaBuilder.like(criteriaBuilder.lower(user.get("firstName")), partialSearch.toLowerCase(Locale.ROOT) + "%");
                Predicate lastName = criteriaBuilder.like(criteriaBuilder.lower(user.get("lastName")), partialSearch.toLowerCase(Locale.ROOT) + "%");
                Predicate email = criteriaBuilder.like(criteriaBuilder.lower(user.get("email")), partialSearch.toLowerCase(Locale.ROOT) + "%");
                predicates.add(criteriaBuilder.or(firstName, lastName, email));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}