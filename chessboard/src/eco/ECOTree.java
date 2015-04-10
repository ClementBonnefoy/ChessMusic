package eco;

import java.util.ArrayList;

import pgn.PGNGame;

public class ECOTree {

	class ECONode {
		private String code;
		private PGNGame codeMoves;
		private ArrayList<ECONode> variations = new ArrayList<ECONode>();
		private ECONode parent;

		public ECONode(String code, PGNGame codeMoves, ECONode parent) {
			super();
			this.code = code;
			this.codeMoves = codeMoves;
			this.parent = parent;
		}

		public String getCode() {
			return code;
		}

		public PGNGame getCodeMoves() {
			return codeMoves;
		}

		public ArrayList<ECONode> getVariations() {
			return variations;
		}
		
		public ECONode getParent() {
			return parent;
		}
		
		public void setParent(ECONode parent) {
			this.parent = parent;
		}

		public void insert(ECONode ecoNode) {
			PGNGame newNodeMoves = ecoNode.getCodeMoves();

			if (codeMoves.size() > newNodeMoves.size())
				throw new RuntimeException("This ("+ codeMoves +")"
						+ " is not a prefix of the new node ("+ newNodeMoves +")");

			if (codeMoves.size() == newNodeMoves.size())
				throw new RuntimeException("This ("+ code+" : "+ codeMoves +")"
						+ " is already present : "+ecoNode.getCode()+" : "+newNodeMoves);

			for (int i = 0; i < codeMoves.size(); i++)
				if (!newNodeMoves.get(i).equals(codeMoves.get(i)))
					throw new RuntimeException("This ("+ codeMoves +")"
							+ " is not a prefix of the new node ("+ newNodeMoves+")");

			PGNGame nodeMoves;
			ECONode node;

			for (int j = 0; j < variations.size(); j++) {
				node = variations.get(j);
				nodeMoves = node.getCodeMoves();
				int i = codeMoves.size();
				while (i < nodeMoves.size() && i < newNodeMoves.size()
						&& nodeMoves.get(i).equals(newNodeMoves.get(i)))
					i++;
				if (i == codeMoves.size())
					continue;
				if (i == nodeMoves.size()) {
					if (i == codeMoves.size())
						throw new RuntimeException("Inserted opening is already present");
					ecoNode.setParent(node);
					node.insert(ecoNode);
					return;
				}
			}

			variations.add(ecoNode);

		}
		
		public String findCode(PGNGame findMoves) {
			PGNGame nodeMoves;
			if (codeMoves.size() == findMoves.size())
				return code;
			if (codeMoves.size() > findMoves.size())
				return parent.getCode();
			for (ECONode node : variations) {
				nodeMoves = node.getCodeMoves();
				int i = codeMoves.size();
				while (i < nodeMoves.size() && i < findMoves.size()
						&& nodeMoves.get(i).equals(findMoves.get(i)))
					i++;
				if (i == nodeMoves.size())
					return node.findCode(findMoves);
			}
			return code;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			ECONode tmp = this;
			while ((tmp = tmp.getParent()) != null)
				sb.append(' ');
			sb.append(code+" : ");
			sb.append(codeMoves.toString());

			sb.append('\n');

			for (ECONode node : variations)
				sb.append(node.toString());
			return sb.toString();

		}

	}

	private ArrayList<ECONode> roots;
	

	public ECOTree() {
		roots = new ArrayList<ECONode>();
	}

	public void insert(String code, PGNGame codeMoves) {

		PGNGame nodeMoves;
		ECONode node;

		for (int j = 0; j < roots.size(); j++) {
			node = roots.get(j);
			nodeMoves = node.getCodeMoves();
			int i = 0;
			while (i < nodeMoves.size() && i < codeMoves.size()
					&& nodeMoves.get(i).equals(codeMoves.get(i)))
				i++;
			if (i == 0)
				continue;
			if (i == nodeMoves.size()) {
				if (i == codeMoves.size())
					throw new RuntimeException("Inserted opening is already present");
				else if (nodeMoves.size() > codeMoves.size())
					throw new RuntimeException("Base opening ("+nodeMoves +") is longer than the new one : "+codeMoves);

				node.insert(new ECONode(code, codeMoves, node));
				return;
			}

		}

		roots.add(new ECONode(code, codeMoves, null));


	}
	
	public String findCode(PGNGame codeMoves) {
		PGNGame nodeMoves;
		for (ECONode node : roots) {
			nodeMoves = node.getCodeMoves();
			int i = 0;
			while (i < nodeMoves.size() && i < codeMoves.size()
					&& nodeMoves.get(i).equals(codeMoves.get(i)))
				i++;
			if (i == nodeMoves.size())
				return node.findCode(codeMoves);
		}
		return "E100";
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (ECONode node : roots)
			sb.append(node.toString());

		return sb.toString();


	}

}
