package ar.edu.unq.ttip.alec.backend.model.rules;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jeasy.rules.mvel.MVELRule;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Accessors(chain = true,fluent = true)
@Setter
@Getter

public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer priority;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConditionAction> when = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConditionAction> then = new ArrayList<>();

    public Rule(){}


    public Rule (String name, String description, Integer priority,
                 List<ConditionAction> when, List<ConditionAction> then){
        this.name=name;
        this.description=description;
        this.priority=priority;
        this.when = when;
        this.then = then;
    }

    public MVELRule toMVEL(){
        MVELRule rule= new MVELRule()
                .name(name)
                .description(description)
                .priority(priority);
        when.stream().forEach(each -> rule.when(each.getValue()));
        then.stream().forEach(each -> rule.then(each.getValue()));
        return rule;
    }

    public String getName() {
        return name;
    }

    public List<String> getWhen() {
        return when.stream().map(i->i.value).collect(Collectors.toList());
    }

    public List<String> getThen() {
        return then.stream().map(i->i.value).collect(Collectors.toList());
    }

    public Integer getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setThen(List<ConditionAction> then) {
        this.then = then;
    }

    public void setWhen(List<ConditionAction> when) {
        this.when = when;
    }

    public void addWhen(ConditionAction condition) {
        when.add (condition);
    }
    public void addThen(ConditionAction condition) {
        then.add (condition);
    }

    public Rule then(String action) {
        this.then.add (new ConditionAction(action));
        return this;
    }
    public Rule when(String action) {
        this.when.add (new ConditionAction(action));
        return this;
    }


    public void setId(Integer id) {
        this.id = id;
    }
}


