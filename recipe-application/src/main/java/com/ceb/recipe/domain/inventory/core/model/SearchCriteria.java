package com.ceb.recipe.domain.inventory.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class SearchCriteria {

    private String title;
    private LocalDateTime createDateSTART;
    private LocalDateTime createDateEND;
    private Boolean vegetarian;
    private Integer suitableForNumberOfPeopleEQUAL;
    private Integer suitableForNumberOfPeopleGT;
    private Integer suitableForNumberOfPeopleGTE;
    private Integer suitableForNumberOfPeopleLT;
    private Integer suitableForNumberOfPeopleLTE;
    private Integer prepareTimeInMinutesEQUAL;
    private Integer prepareTimeInMinutesGT;
    private Integer prepareTimeInMinutesGTE;
    private Integer prepareTimeInMinutesLT;
    private Integer prepareTimeInMinutesLTE;
    private Integer cookingTimeInMinutesEQUAL;
    private Integer cookingTimeInMinutesGT;
    private Integer cookingTimeInMinutesGTE;
    private Integer cookingTimeInMinutesLT;
    private Integer cookingTimeInMinutesLTE;
    private Integer caloriesEQUAL;
    private Integer caloriesGT;
    private Integer caloriesGTE;
    private Integer caloriesLT;
    private Integer caloriesLTE;
    private Boolean includeIngredients;
    private Boolean includeCookingInstructions;
    private List<String> users;

    public boolean searchByTitle() {
        return StringUtils.isNoneBlank(this.title);
    }

    public boolean searchByDateRange() {
        return ObjectUtils.anyNotNull(this.createDateSTART, this.createDateEND);
    }

    public boolean searchByVegetarian() {
        return Objects.nonNull(this.vegetarian);
    }

    public boolean searchBySuitableForNumberEqual() {
        return Objects.nonNull(this.suitableForNumberOfPeopleEQUAL);
    }

    public boolean searchBySuitableForNumberRange() {
        return !this.searchBySuitableForNumberEqual() && ObjectUtils.anyNotNull(this.getSuitableForNumberOfPeopleGT(), this.getSuitableForNumberOfPeopleGTE(), this.getSuitableForNumberOfPeopleLT(), this.getSuitableForNumberOfPeopleLTE());
    }

    public boolean searchByPrepareTimeEQUAL() {
        return Objects.nonNull(this.getPrepareTimeInMinutesEQUAL());
    }

    public boolean searchByPrepareTimeRange() {
        return !this.searchByPrepareTimeEQUAL() && ObjectUtils.anyNotNull(this.getPrepareTimeInMinutesGT(), this.getPrepareTimeInMinutesGTE(), this.getPrepareTimeInMinutesLT(), this.getPrepareTimeInMinutesLTE());
    }

    public boolean searchByCookingTimeEQUAL() {
        return Objects.nonNull(this.getCookingTimeInMinutesEQUAL());
    }

    public boolean searchByCookingTimeRange() {
        return !this.searchByCookingTimeEQUAL() && ObjectUtils.anyNotNull(this.getCookingTimeInMinutesGT(), this.getCookingTimeInMinutesGTE(), this.getCookingTimeInMinutesLT(), this.getCookingTimeInMinutesLTE());
    }

    public boolean searchByCaloriesEQUAL() {
        return Objects.nonNull(this.getCaloriesEQUAL());
    }

    public boolean searchByCaloriesRange() {
        return !this.searchByCaloriesEQUAL() && ObjectUtils.anyNotNull(this.getCaloriesGT(), this.getCaloriesGTE(), this.getCaloriesLT(), this.getCaloriesLTE());
    }

    public boolean searchByUsers() {
        return Objects.nonNull(this.getUsers()) && !this.getUsers().isEmpty();
    }


    public boolean includeIngredients() {
       return Objects.nonNull(this.includeIngredients) && this.includeIngredients;
    }

    public boolean includeCookingInstructions() {
        return Objects.nonNull(this.includeCookingInstructions) && this.includeCookingInstructions;
    }
}
