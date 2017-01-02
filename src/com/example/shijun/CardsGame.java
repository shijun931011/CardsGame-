package com.example.shijun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CardsGame {
	// ÿ���˿���
	String[] dataList = { "����1", "����2", "����3", "����4", "����5", "����6", "����7", "����8", "����9", "����10", "����J", "����Q", "����K",
			"÷��1", "÷��2", "÷��3", "÷��4", "÷��5", "÷��6", "÷��7", "÷��8", "÷��9", "÷��10", "÷��J", "÷��Q", "÷��K", "����1", "����2",
			"����3", "����4", "����5", "����6", "����7", "����8", "����9", "����10", "����J", "����Q", "����K", "����1", "����2", "����3", "����4",
			"����5", "����6", "����7", "����8", "����9", "����10", "����J", "����Q", "����K" };

	// һ����
	List<String> cardsList = new ArrayList<String>();
	// ��ɫ
	String[] suitList = { "����", "÷��", "����", "����" };
	// ����б�
	static List<People> playerList = new ArrayList<People>();

	// ����һ���ƣ�������⸱�Ƶ�ÿ����
	public void createCards() {
		for (int i = 0; i < dataList.length; i++) {
			cardsList.add(dataList[i]);
		}
		System.out.println("�����˿������");
		System.out.println("�˿������У�");
		for (String string : cardsList) {
			System.out.print(string + " ");
		}
	}

	// ϴ�ƣ�����˳��
	public void shuffle() {
		Collections.shuffle(cardsList);
		System.out.println();
		System.out.println("ϴ�����");
		System.out.print("�˿������У�");
		for (String string : cardsList) {
			System.out.print(string + " ");
		}
	}

	// ���ƣ�ÿ������
	public void dealCards() {
		System.out.println();
		System.out.println("��������λ��ҵ�����");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2; i++) {
			System.out.println("�������" + (i + 1) + "����ҵ�����");
			String name = scanner.next();
			People player = new People(name);
			playerList.add(player);
		}
		for (People people : playerList) {
			System.out.print("��ҷֱ��ǣ�");
			System.out.println(people.name);
		}
		System.out.println("--------------------------");
		// ����
		for (int i = 0; i < 4; i++) {
			String tempCard = cardsList.get(i);
			if (i % 2 == 0) {
				playerList.get(0).cards.add(tempCard);
			} else {
				playerList.get(1).cards.add(tempCard);
			}
		}
		// ����
		for (People people : playerList) {
			System.out.print("��ҷֱ��ǣ�" + people.name + " ��ҵ������ǣ�" + people.cards);
			System.out.println();
		}

	}

	// ���ƣ�����ʤ��
	public void whoWin(List<People> playerList) {
		// ���
		People player1 = playerList.get(0);
		People player2 = playerList.get(1);
		// ���1��������
		String card1ForPlayer1 = player1.cards.get(0);
		String card2ForPlayer1 = player1.cards.get(1);
		// ���1������
		String bigCardForPlayer1 = getBigCard(card1ForPlayer1, card2ForPlayer1);
		System.out.println("--------------------------");
		System.out.println(player1.name + " ������Ϊ��" + bigCardForPlayer1);
		// ���2��������
		String card1ForPlayer2 = player2.cards.get(0);
		String card2ForPlayer2 = player2.cards.get(1);
		// ���2������
		String bigCardForPlayer2 = getBigCard(card1ForPlayer2, card2ForPlayer2);
		System.out.println(player2.name + " ������Ϊ��" + bigCardForPlayer2);
		System.out.println("--------------------------");
		// �����������
		String bigCard = getBigCard(bigCardForPlayer1, bigCardForPlayer2);
		if (bigCardForPlayer1.equals(bigCard)) {
			System.out.println("��ʤ���ǣ�" + player1.name);
		} else if (bigCardForPlayer2.equals(bigCard)) {
			System.out.println("��ʤ���ǣ�" + player2.name);
		}
	}

	// �Ƚ���������������
	public String getBigCard(String card1, String card2) {
		// ����������
		String bigCard = null;
		// ȡ��ÿ���Ƶ����֣���Ϊ������10�����Դӵڶ�λ�����ȡ�ַ���
		String num1 = card1.substring(2);
		String num2 = card2.substring(2);
		// ȡ��ÿ���ƵĻ�ɫ
		String suit1 = card1.substring(0, 2);
		String suit2 = card2.substring(0, 2);
		// �����ƵĻ�ɫ��С
		int level1 = 0;
		int level2 = 0;
		// ���ÿ���Ƶ����ֺͻ�ɫ
		// System.out.println("���ֱַ�Ϊ��" + num1 + " " + num2);
		// System.out.println("��ɫ�ֱ�Ϊ��" + suit1 + " " + suit2);
		if (num1.compareTo(num2) > 0) {
			// System.out.println("��һ���ƴ�");
			bigCard = card1;
		} else if (num1.compareTo(num2) == 0) {
			// System.out.println("������һ����");
			// ��ͬ�����£��Ƚϻ�ɫ
			for (int i = 0; i < suitList.length; i++) {
				if (suit1.equals(suitList[i])) {
					// �����ɫ�������е��±꣬�������ĸ���ɫ��
					// System.out.println(i);
					level1 = i;
				}
				if (suit2.equals(suitList[i])) {
					// �����ɫ�������е��±꣬�������ĸ���ɫ��
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
			// System.out.println("�ڶ����ƴ�");
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
