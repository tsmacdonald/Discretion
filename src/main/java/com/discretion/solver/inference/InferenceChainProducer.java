package com.discretion.solver.inference;

import com.discretion.proof.ProofItem;
import com.discretion.solver.environment.TruthEnvironment;
import com.discretion.statement.Statement;

import java.util.List;

/**
 * Given a set of Statements assumed to be true, creates a chain of inferences that
 * correctly produces a desired conclusion.
 *
 */
public interface InferenceChainProducer {
    List<ProofItem> buildInferenceChain(Statement conclusion, TruthEnvironment environment);
}
