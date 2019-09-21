import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SunTec {

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		System.out.println("Please Enter How many players do you want add");
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		for (int i = 0; i < count; i++) {
			System.out.println("Enter Player Name");
			Player player = new Player();
			player.setName(scanner.next());
			System.out.println("Enter Player scores in comma seperated");
			String s = scanner.next();
			String[] scores = s.split(",");
			player.setScores(Stream.of(scores).map(Integer::parseInt).collect(Collectors.toList()));
			players.add(player);
		}
		do {
			System.out.println("Enter name of the Player to whom you want see to leaderborad postion");
			String input = scanner.next();
			System.out.println(findPlayerPosition(players, input));
			System.out.println("Do you want to continue Y/N");
		} while (scanner.next().equalsIgnoreCase("Y"));
	}

	private static int findPlayerPosition(List<Player> players, String input) {
		int result = -1;// return -1 if we give incorrect Player names
		List<Integer> list = new ArrayList<>();
		Map<Integer, List<String>> map = new HashMap<>();
		for (int i = 0; i < players.size(); i++) {
			int highest = players.get(i).getScores().stream().max(Comparator.naturalOrder()).get();
			if (map.containsKey(highest)) {
				List<String> ss = new ArrayList<>();
				ss.addAll(map.get(highest));
				ss.add(players.get(i).getName());
				map.put(highest, ss);
			} else {
				map.put(highest, Arrays.asList(players.get(i).getName()));
				list.add(highest);
			}

		}
		Optional<Integer> key = map.entrySet().stream().filter(entry -> entry.getValue().contains(input))
				.map(Map.Entry::getKey).findFirst();
		if (key.isPresent()) {
			Collections.sort(list);
			result = list.size() - list.indexOf(key.get());
		}
		return result;
	}

}
