package Task_1;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Task_1 {
    private static final Map<Character, HashSet<Character>> graph = new HashMap<>();
    private static final int[] used = new int[26];
    private static final ArrayList<Character> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] names = new String[n];
        String[] nameAndId;
        String line;

        for (int i = 0; i < n; i++) {
            line = scanner.next();
            nameAndId = line.split("%");
            names[Integer.parseInt(nameAndId[1]) - 1] = nameAndId[0].toLowerCase(Locale.ROOT);
        }

        try {
            makeGraph(names);
            topological_sort();

            for (char c : ans) {
                System.out.print(c + " ");
            }
        } catch (Exception e) {
            System.out.println("Impossible");
        }
    }


    /**
     * Creates a new graph (a list of letter adjacency) from an array of words.
     *
     * @param names Array of strings from which the adjacency list is created.
     * @throws StringIndexOutOfBoundsException if string n + 1 is a substring of string n
     */
    public static void makeGraph(String[] names) throws StringIndexOutOfBoundsException {
        String first;
        String second;
        for (int i = 0; i < names.length; i++) {
            first = names[i];
            for (int j = i + 1; j < names.length; j++) {
                second = names[j];
                for (int k = 0; k < first.length(); k++) {
                    char first_c = first.charAt(k);
                    char second_c = second.charAt(k);
                    if (first_c != second_c) {
                        if (graph.containsKey(first_c)) {
                            graph.get(first_c).add(second_c);
                        } else {
                            HashSet<Character> chars = new HashSet<>();
                            chars.add(second_c);
                            graph.put(first_c, chars);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Depth-first search.
     *
     * @param v the vertex from which to start the depth-first search
     * @throws Exception if we have a cycle
     */
    public static void dfs(char v) throws Exception {
        used[v % 0x61] = 1;
        if (graph.containsKey(v)) {
            Set<Character> chars = graph.get(v);
            for (char c : chars) {
                if (used[c % 0x61] == 0) {
                    dfs(c);
                } else if (used[c % 0x61] == 1) {
                    throw new Exception("Cyclic");
                }
            }
        }
        used[v % 0x61] = 2;
        ans.add(v);
    }

    /**
     * Topological sorting
     *
     * @throws Exception if we have a cycle
     */
    public static void topological_sort() throws Exception {
        for (int i = 0; i < 26; i++) {
            used[i] = 0;
        }
        for (int i = 0; i < 26; i++) {
            if (!(used[i] == 2)) {
                dfs((char) (i + 0x61));
            }
        }
        Collections.reverse(ans);
    }
}
