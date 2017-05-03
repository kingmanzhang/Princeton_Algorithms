
import java.util.Iterator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	

   
   private class SearchNode implements Comparable<SearchNode> {
   	
   	private SearchNode previous;
   	private Board board;
   	private int moves;
   	private int priority;
   	
   	private SearchNode(Board board) {
   		this.previous = null;
   		this.board = board;
   		this.moves = 0;
   		this.priority = board.manhattan();
   	}
   	
   	private SearchNode(Board board, SearchNode previous) {
   		this.previous = previous;
   		this.board = board;
   		this.moves = previous.getMoves() + 1;
   		this.priority = board.manhattan() + this.moves;
	
   	}
   	public int getMoves() {
   		return moves;
   	}
   	
   		
   	public SearchNode getPrevious () {
   		
   		return previous;
   	
   	}

		public Board getBoard() {
			
			return board;
			
		}
		
		public int getPriority() {
			return priority;
		}
		
public String toString() {
			return "moves: " + this.getMoves() + " manhantan: " + this.board.manhattan() + " hamming: " + this.board.hamming() + "\n"+ this.board;
		}
		

		@Override
		public int compareTo(SearchNode o) {
			
			return this.getPriority() - o.getPriority();
		}
   	
   }
   
	
	private int totalMoves;
	private MinPQ<SearchNode> gameQueue;
	private MinPQ<SearchNode> twinQueue;
	private Stack<Board> solution;
	private boolean isSolvable;
	
   public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
   	
   	//this.initial = initial;
   	this.totalMoves = 0;
   	this.gameQueue = new MinPQ<>();
   	this.twinQueue = new MinPQ<>();
   	SearchNode initialNode = new SearchNode(initial);
   	SearchNode twinInitialNode = new SearchNode(initial.twin());
   	gameQueue.insert(initialNode);
   	twinQueue.insert(twinInitialNode);
   	this.solution = new Stack<>();
   	this.isSolvable = false;
   	
   	solve();
   }
   
   private void solve() {
   	boolean completed = false;
   	while (!completed) { // when solvability can be determined, loop is completed
   		SearchNode searchnode = gameQueue.delMin();
   		SearchNode twinSearchnode = twinQueue.delMin();
		
   		if (searchnode.getBoard().isGoal()) {
   			completed = true;
   			isSolvable = true;
   			this.totalMoves = searchnode.getMoves();
   			solution.push(searchnode.getBoard());
   			while(searchnode.getPrevious() != null) {
   				searchnode = searchnode.getPrevious();
   				solution.push(searchnode.getBoard());
   			}
   			return;
   		} else {
   			for (Board neighbor: searchnode.getBoard().neighbors()) {
   				if (searchnode.getPrevious() == null ||
   						 !neighbor.equals(searchnode.getPrevious().getBoard())) {
   					gameQueue.insert(new SearchNode(neighbor, searchnode));
   				}
   			}
   		}
   		
   		if (twinSearchnode.getBoard().isGoal()) {
   			completed = true;
   			isSolvable = false;
   			return;
   		} else {
   			for (Board neighbor: twinSearchnode.getBoard().neighbors()) {
   				if (twinSearchnode.getPrevious() == null ||
   						 !neighbor.equals(twinSearchnode.getPrevious().getBoard())) {
   					twinQueue.insert(new SearchNode(neighbor, twinSearchnode));
   				}
   			}
   		}	
   	}
   }
   
   public boolean isSolvable() {            // is the initial board solvable?
   	
   	return isSolvable;
   	
   }
   
   public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
   	if(!isSolvable) {
   		return -1;
   	}
   	return totalMoves;
   }
   
   public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
   	
   	if (!isSolvable) {
   		return null;
   	}
   	
   	return solution;
   	
   }
   public static void main(String[] args) { // solve a slider puzzle (given below)
   	int[][] initialBlock = new int[][] {{3, 1, 2}, {4, 5, 6}, {7, 8, 0}};
   			// {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
   			// {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};

		Board initial = new Board(initialBlock);
		Solver test = new Solver(initial);
		System.out.println("is solvable: " + test.isSolvable());
		System.out.println("moves: " + test.moves());
		
		if (test.isSolvable()) {
			Iterator<Board> itr = test.solution().iterator();
	   	while (itr.hasNext()) {
	   		System.out.println(itr.next() + "\n");
	   	}
		}	
   }
}
