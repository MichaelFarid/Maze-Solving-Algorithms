import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS
{
	public static void main(String[] args)
	{
		int[][] maze = { { 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };
		System.out.println(BFSDist(maze, new point(0, 4), new point(4, 4)));
	}

	static ArrayList<point> BFSpath(int[][] maze, point start, point end)
	{
		ArrayList<point> path = new ArrayList<>();
		Queue<point> next = new LinkedList<point>();
		int[][] road = maze.clone();
		next.offer(start);
		while (next.peek() != null)
		{
			point current = next.poll();
			road[current.x][current.y] = 1;
			if (current.x == end.x && current.y == end.y)
			{
				int totaldist = current.dist;
				for (int i = 0; i < totaldist; i++)
				{
					path.add(current);
					current = current.previous;
				}
				break;
			}
			if (current.x < road.length - 1 && road[current.x + 1][current.y] == 0)
			{
				point neighbour = new point(current.x + 1, current.y, current);
				next.offer(neighbour);
			}
			if (current.x - 1 > -1 && road[current.x - 1][current.y] == 0)
			{
				next.offer(new point(current.x - 1, current.y, current));
			}
			if (current.y < road[0].length - 1 && road[current.x][current.y + 1] == 0)
			{
				next.offer(new point(current.x, current.y + 1, current));
			}
			if (current.y - 1 > -1 && road[current.x][current.y - 1] == 0)
			{
				next.offer(new point(current.x, current.y - 1, current));
			}
		}
		return path;
	}

	static int BFSDist(int[][] maze, point start, point end)
	{
		Queue<point> next = new LinkedList<point>();
		int[][] road = maze.clone();
		road[start.x][start.y] = 1;
		next.offer(start);
		while (next.peek() != null)
		{
			point current = next.poll();
			if (current.x == end.x && current.y == end.y)
			{
				return current.dist;
			}
			road[current.x][current.y] = 1;
			if (current.x < road.length - 1 && road[current.x + 1][current.y] == 0)
			{
				next.offer(new point(current.x + 1, current.y, current));
			}
			if (current.x - 1 > -1 && road[current.x - 1][current.y] == 0)
			{
				next.offer(new point(current.x - 1, current.y, current));
			}
			if (current.y < road[0].length - 1 && road[current.x][current.y + 1] == 0)
			{
				next.offer(new point(current.x, current.y + 1, current));
			}
			if (current.y - 1 > -1 && road[current.x][current.y - 1] == 0)
			{
				next.offer(new point(current.x, current.y - 1, current));
			}
		}
		return -1;
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
