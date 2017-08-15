import java.util.Stack;

public class DFS
{
	public static void main(String[] args)
	{
		int[][] maze = { { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		Stack<point> solxn = new Stack<>();
		solxn = DFS(maze, new point(0, 0), new point(4, 4));
		while (!solxn.isEmpty())
		{
			point step = solxn.pop();
			maze[step.x][step.y] = 7;
		}
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[0].length; j++)
			{
				System.out.print(maze[i][j]);
			}
			System.out.println();

		}

	}

	public static Stack<point> DFS(int[][] maze, point start, point end)
	{
		int[][] path = maze.clone();
		Stack<point> inPath = new Stack<>();
		inPath.push(start);
		return DFS(maze, end, inPath);

	}

	public static Stack<point> DFS(int[][] maze, point end, Stack<point> stack)
	{
		point current = stack.peek();
		if (current.x == end.x && current.y == end.y)
		{
			return stack;
		}
		if (current.x + 1 < maze.length && maze[current.x + 1][current.y] == 0)
		{
			stack.push(new point(current.x + 1, current.y));
			maze[current.x + 1][current.y] = 1;
			return DFS(maze, end, stack);
		}
		else if (current.x - 1 > -1 && maze[current.x - 1][current.y] == 0)
		{
			stack.push(new point(current.x - 1, current.y));
			maze[current.x - 1][current.y] = 1;
			return DFS(maze, end, stack);
		}
		else if (current.y + 1 < maze[0].length && maze[current.x][current.y + 1] == 0)
		{
			stack.push(new point(current.x, current.y + 1));
			maze[current.x][current.y + 1] = 1;
			return DFS(maze, end, stack);
		}
		else if (current.y - 1 > -1 && maze[current.x][current.y - 1] == 0)
		{
			stack.push(new point(current.x, current.y - 1));
			maze[current.x][current.y - 1] = 1;
			return DFS(maze, end, stack);
		}
		else
		{
			stack.pop();
			return DFS(maze, end, stack);
		}
	}

	static class point implements Cloneable
	{
		int x;
		int y;
		int dist;
		point previous;

		point(int x, int y)
		{
			this.x = x;
			this.y = y;
			dist = 0;
		}

		point(int x, int y, point previous)
		{
			this.x = x;
			this.y = y;
			dist = previous.dist + 1;
			this.previous = previous;
		}
	}
}
