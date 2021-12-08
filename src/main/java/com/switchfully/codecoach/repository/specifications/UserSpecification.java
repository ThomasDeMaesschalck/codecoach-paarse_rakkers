/*
package com.switchfully.codecoach.repository.specifications;

import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.User;
import com.switchfully.codecoach.domain.Topic;
import com.switchfully.codecoach.domain.UserRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Component
public class UserSpecification {

    private List<Predicate> predicates;


    public Specification<User> getUsers(Topic.TopicName topicName, UserRole userRole) {
        return (user, query, criteriaBuilder) -> {

            predicates = new ArrayList<>();

            addTopicNameToQuery(topicName, user, criteriaBuilder);
            addUserRoleToQuery(userRole, user, criteriaBuilder);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private void addTopicNameToQuery(Topic.TopicName topicName, Root<User> user, CriteriaBuilder criteriaBuilder) {

        if (topicName != null) {
            predicates.add(criteriaBuilder.equal(user.get("coachInfo").get("topics"), topicName));
        }


    }

    private void addUserRoleToQuery(UserRole userRole, Root<User> user, CriteriaBuilder criteriaBuilder) {
        if (userRole != null) {
            predicates.add(criteriaBuilder.equal(user.get("userRole"), userRole));
        }
    }


}*/
