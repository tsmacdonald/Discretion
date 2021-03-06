package com.discretion.proof;

import com.discretion.statement.Statement;

import java.util.LinkedList;
import java.util.List;

public class Proof implements ProofItem {

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public List<Statement> getSuppositions() {
        return suppositions;
    }

    public void setSuppositions(List<Statement> suppositions) {
        this.suppositions = suppositions;
    }

    public List<ProofItem> getProofItems() {
        return proof;
    }

    public void setProofItems(List<ProofItem> proof) {
        this.proof = proof;
    }

    public ProofStatement getConclusion() {
        return conclusion;
    }

    public void setConclusion(Statement conclusion) {
        this.conclusion = new ProofStatement(conclusion);
    }

    public void setConclusion(ProofStatement conclusion) {
        this.conclusion = conclusion;
    }

    public void accept(ProofItemVisitor visitor) {
        visitor.visit(this);
    }

	public Proof(List<Statement> suppositions, List<ProofItem> proof, Statement conclusion) {
		this.suppositions = suppositions;
		this.proof = proof;
		this.conclusion = new ProofStatement(conclusion);
	}

	public Proof(List<Statement> suppositions, List<ProofItem> proof, ProofStatement conclusion) {
		this.suppositions = suppositions;
		this.proof = proof;
		this.conclusion = conclusion;
	}

	public Proof(List<Statement> suppositions, List<ProofItem> proof) {
		this.suppositions = suppositions;

		this.proof = new LinkedList<>();
		this.proof.addAll(proof);
		this.conclusion = (ProofStatement)this.proof.remove(proof.size()-1);
	}

	public Proof() {
        suppositions = new LinkedList<>();
        proof = new LinkedList<>();
    }

	private String title;
    private List<Statement> suppositions;
    private List<ProofItem> proof;
    private ProofStatement conclusion;
}
