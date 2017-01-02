package com.example.shijun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CardsGame {
	// 每张扑克牌
	String[] dataList = { "方块1", "方块2", "方块3", "方块4", "方块5", "方块6", "方块7", "方块8", "方块9", "方块10", "方块J", "方块Q", "方块K",
			"梅花1", "梅花2", "梅花3", "梅花4", "梅花5", "梅花6", "梅花7", "梅花8", "梅花9", "梅花10", "梅花J", "梅花Q", "梅花K", "红桃1", "红桃2",
			"红桃3", "红桃4", "红桃5", "红桃6", "红桃7", "红桃8", "红桃9", "红桃10", "红桃J", "红桃Q", "红桃K", "黑桃1", "黑桃2", "黑桃3", "黑桃4",
			"黑桃5", "黑桃6", "黑桃7", "黑桃8", "黑桃9", "黑桃10", "黑桃J", "黑桃Q", "黑桃K" };

	// 一副牌
	List<String> cardsList = new ArrayList<String>();
	// 花色
	String[] suitList = { "方块", "梅花", "红桃", "黑桃" };
	// 玩家列表
	static List<People> playerList = new ArrayList<People>();

	// 创建一副牌，并输出这副牌的每副牌
	public void createCards() {
		for (int i = 0; i < dataList.length; i++) {
			cardsList.add(dataList[i]);
		}
		System.out.println("创建扑克牌完成");
		System.out.println("扑克牌里有：");
		for (String string : cardsList) {
			System.out.print(string + " ");
		}
	}

	// 洗牌，打乱顺序
	public void shuffle() {
		Collections.shuffle(cardsList);
		System.out.println();
		System.out.println("洗牌完成");
		System.out.print("扑克牌里有：");
		for (String string : cardsList) {
			System.out.print(string + " ");
		}
	}

	// 发牌，每人两张
	public void dealCards() {
		System.out.println();
		System.out.println("请输入两位玩家的名字");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2; i++) {
			System.out.println("请输入第" + (i + 1) + "个玩家的名字");
			String name = scanner.next();
			People player = new People(name);
			playerList.add(player);
		}
		for (People people : playerList) {
			System.out.print("玩家分别是：");
			System.out.println(people.name);
		}
		System.out.println("--------------------------");
		// 发牌
		for (int i = 0; i < 4; i++) {
			String tempCard = cardsList.get(i);
			if (i % 2 == 0) {
				playerList.get(0).cards.add(tempCard);
			} else {
				playerList.get(1).cards.add(tempCard);
			}
		}
		// 亮牌
		for (People people : playerList) {
			System.out.print("玩家分别是：" + people.name + " 玩家的手牌是：" + people.cards);
			System.out.println();
		}

	}

	// 比牌，决定胜负
	public void whoWin(List<People> playerList) {
		// 玩家
		People player1 = playerList.get(0);
		People player2 = playerList.get(1);
		// 玩家1的两张牌
		String card1ForPlayer1 = player1.cards.get(0);
		String card2ForPlayer1 = player1.cards.get(1);
		// 玩家1最大的牌
		String bigCardForPlayer1 = getBigCard(card1ForPlayer1, card2ForPlayer1);
		System.out.println("--------------------------");
		System.out.println(player1.name + " 最大的牌为：" + bigCardForPlayer1);
		// 玩家2的两张牌
		String card1ForPlayer2 = player2.cards.get(0);
		String card2ForPlayer2 = player2.cards.get(1);
		// 玩家2最大的牌
		String bigCardForPlayer2 = getBigCard(card1ForPlayer2, card2ForPlayer2);
		System.out.println(player2.name + " 最大的牌为：" + bigCardForPlayer2);
		System.out.println("--------------------------");
		// 玩家中最大的牌
		String bigCard = getBigCard(bigCardForPlayer1, bigCardForPlayer2);
		if (bigCardForPlayer1.equals(bigCard)) {
			System.out.println("获胜的是：" + player1.name);
		} else if (bigCardForPlayer2.equals(bigCard)) {
			System.out.println("获胜的是：" + player2.name);
		}
	}

	// 比较两张牌最大的那张
	public String getBigCard(String card1, String card2) {
		// 最大的那张牌
		String bigCard = null;
		// 取得每张牌的数字，因为有数字10，所以从第二位往后截取字符串
		String num1 = card1.substring(2);
		String num2 = card2.substring(2);
		// 取得每张牌的花色
		String suit1 = card1.substring(0, 2);
		String suit2 = card2.substring(0, 2);
		// 两张牌的花色大小
		int level1 = 0;
		int level2 = 0;
		// 输出每张牌的数字和花色
		// System.out.println("数字分别为：" + num1 + " " + num2);
		// System.out.println("花色分别为：" + suit1 + " " + suit2);
		if (num1.compareTo(num2) > 0) {
			// System.out.println("第一张牌大");
			bigCard = card1;
		} else if (num1.compareTo(num2) == 0) {
			// System.out.println("两张牌一样大");
			// 相同数字下，比较花色
			for (int i = 0; i < suitList.length; i++) {
				if (suit1.equals(suitList[i])) {
					// 输出花色在数组中的下标，来决定哪个花色大
					// System.out.println(i);
					level1 = i;
				}
				if (suit2.equals(suitList[i])) {
					// 输出花色在数组中的下标，来决定哪个花色大
					// System.out.println(i);
					level2 = i;
				}
			}
			if (level1 > level2) {
				// System.out.println(card1);
				bigCard = card1;
			} else {
				// System.out.println(card2);
				bigCard = card2;
			}

		} else if (num1.compareTo(num2) < 0) {
			// System.out.println("第二张牌大");
			bigCard = card2;
		}
		return bigCard;
	}

	public static void main(String[] args) {
		CardsGame cg = new CardsGame();
		cg.createCards();
		cg.shuffle();
		cg.dealCards();
		cg.whoWin(playerList);
	}

}
