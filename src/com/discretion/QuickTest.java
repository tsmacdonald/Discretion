package com.discretion;

import com.discretion.expression.SetUnion;
import com.discretion.proof.Proof;
import com.discretion.proof.ProofItem;
import com.discretion.proof.ProofStatement;
import com.discretion.solver.PartialSolver;
import com.discretion.solver.Solver;
import com.discretion.statement.ElementOf;
import com.discretion.statement.Equality;
import com.discretion.statement.Statement;
import com.discretion.statement.SubsetOf;

import java.util.LinkedList;

public class QuickTest {
    public static void main(String... args) {
        ProofPrinter printer = new ProofPrinter(new PrettyPrinter());

        Variable setX = new Variable("X");
        Variable setY = new Variable("Y");
        Variable setZ = new Variable("Z");

        LinkedList<Statement> supps = new LinkedList<>();
        supps.add(new SubsetOf(setX, setY));
        supps.add(new SubsetOf(setY, setZ));

        // The sub-proof: if x is in X, then x is in Y, so x is in Z
        Variable x = new Variable("x");
        LinkedList<Statement> subSuppositions = new LinkedList<>();
        subSuppositions.add(new ElementOf(x, setX));
        LinkedList<ProofItem> subStatements = new LinkedList<>();
        subStatements.add(new ProofStatement(
                new ElementOf(x, setY)
        ));
        Statement subConclusion = new ElementOf(x, setZ);
        Proof subProof = new Proof(subSuppositions, subStatements, subConclusion);

        LinkedList<ProofItem> statements = new LinkedList<>();
        statements.add(subProof);
        Statement conclusion = new SubsetOf(setX, setZ);

        Proof proof = new Proof(supps, statements, conclusion);

        System.out.println("***Pretty print a proof**\n");
        printer.prettyPrint(proof);

        System.out.println("\n***Try to solve a proof**");
        Solver solver = new PartialSolver();
        Proof partialProof = solver.solve(conclusion, supps);
        printer.prettyPrint(partialProof);


        System.out.println("\n***Try to solve a proof**");
        supps = new LinkedList<>();
        supps.add(new SubsetOf(new Variable("A"), new Variable("U")));
        supps.add(new SubsetOf(new Variable("B"), new Variable("U")));
        supps.add(new SubsetOf(new Variable("C"), new Variable("U")));
        conclusion = new Equality(
                new SetUnion(new Variable("A"), new SetUnion(new Variable("B"), new Variable("C"))),
                new SetUnion(new SetUnion(new Variable("A"), new Variable("B")), new Variable("C"))
        );
        partialProof = solver.solve(conclusion, supps);
        printer.prettyPrint(partialProof);
    }
}
