package com.discretion.solver.inference;

import com.discretion.MathObject;
import com.discretion.solver.TruthEnvironment;
import com.discretion.statement.ElementOf;
import com.discretion.statement.Statement;
import com.discretion.statement.SubsetOf;

import java.util.LinkedList;
import java.util.List;

/**
 * Infers that an element of any set is also an element of that set's Superset
 *
 * x elementOf X and X subsetOf Y
 * implies x elementOf Y
 */
public class ElementOfSuperset implements Inference {
    public List<Statement> getInferences(TruthEnvironment environment) {
        List<Statement> inferences = new LinkedList<>();

        // Find any truths of the form "x element of X"
        List<ElementOf> elementOfs = (List<ElementOf>)environment.getTruths(ElementOf.class);
        for (ElementOf elementOf : elementOfs) {
            MathObject set = elementOf.getSet();
            // Find any subset truths whose subset matches this set
            List<SubsetOf> subsetOfs = (List<SubsetOf>)environment.getTruths(SubsetOf.class);
            for (SubsetOf subsetOf : subsetOfs) {
                if (subsetOf.getSubset().equals(set)) {
                    // We found a matching subset statement and can make an inference
                    inferences.add(new ElementOf(elementOf.getElement(), subsetOf.getSet()));
                }
            }
        }

        return inferences;
    }
}
