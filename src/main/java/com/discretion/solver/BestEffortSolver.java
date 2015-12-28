package com.discretion.solver;

import com.discretion.proof.Proof;
import com.discretion.proof.ProofItem;
import com.discretion.solver.environment.NestedTruthEnvironment;
import com.discretion.solver.environment.TruthEnvironment;
import com.discretion.solver.inference.*;
import com.discretion.solver.structure.ProofStructureProducer;
import com.discretion.solver.structure.SetEqualityStructure;
import com.discretion.solver.structure.SubsetStructure;
import com.discretion.statement.Statement;

import java.util.LinkedList;
import java.util.List;

public class BestEffortSolver extends StructuredSolver {

    public BestEffortSolver() {
        inference = new BestEffortInferenceChain();
    }

	@Override
	protected Proof fleshOutProof(List<Statement> given, Statement conclusion, TruthEnvironment environment) {
		List<ProofItem> statements = inference.buildInferenceChain(conclusion, environment);
		return new Proof(given, statements);
	}

    private InferenceChainProducer inference;
}
