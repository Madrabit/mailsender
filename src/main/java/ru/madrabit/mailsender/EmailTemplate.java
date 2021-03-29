package ru.madrabit.mailsender;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EmailTemplate {
   private  String template = "" +
           "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>\n" +
           "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
           "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
           "    <style>\n" +
           "\n" +
           "        @font-face {\n" +
           "            font-family: \"Roboto\";\n" +
           "            src: url( \"https://ibdarb.ru/upload/fonts/Roboto-Regular.ttf\" ) format( \"ttf\" );\n" +
           "            font-style: normal;\n" +
           "            font-weight: normal;\n" +
           "        }\n" +
           "\n" +
           "        @font-face {\n" +
           "            font-family: \"Roboto\";\n" +
           "            src: url( \"https://ibdarb.ru/upload/fonts/Roboto-Bold.ttf\" ) format( \"ttf\" );\n" +
           "            font-style: normal;\n" +
           "            font-weight: bold;\n" +
           "        }\n" +
           "\n" +
           "        @font-face {\n" +
           "            font-family: \"Roboto\";\n" +
           "            src: url( \"https://ibdarb.ru/upload/fonts/Roboto-Italic.ttf\" ) format( \"ttf\" );\n" +
           "            font-style: italic;\n" +
           "            font-weight: normal;\n" +
           "        }\n" +
           "    \n" +
           "        body {\n" +
           "            margin: 0;\n" +
           "            padding: 0;\n" +
           "            background-color: rgb(250, 250, 250);\n" +
           "            font-family: 'Roboto', sans-serif;\n" +
           "            color: #2d2d2d;\n" +
           "        }\n" +
           "\n" +
           "        a {\n" +
           "            font-weight: normal;\n" +
           "        }\n" +
           "    \n" +
           "        li {\n" +
           "            margin-bottom: 5px;\n" +
           "        }\n" +
           "\n" +
           "        .button {\n" +
           "            display: inline-block;\n" +
           "            background-color: #17a2e6;\n" +
           "            border-radius: 6px;\n" +
           "            color: #FFFFFF;\n" +
           "            text-decoration: none;\n" +
           "            padding: 5px 20px;\n" +
           "            margin: 15px 0;\n" +
           "            font-size: 18px;\n" +
           "            box-shadow: 0 10px 20px #bbdbeb;\n" +
           "            /* font-weight: bold; */\n" +
           "        }\n" +
           "        \n" +
           "        .button:hover {\n" +
           "            background-color: #2c97cc;\n" +
           "        }\n" +
           "        .button:active {\n" +
           "            background-color: #4faedd;\n" +
           "        }\n" +
           "    \n" +
           "        .button-blank {\n" +
           "            display: inline-block;\n" +
           "            background-color: transparent;\n" +
           "            border-radius: 60px;\n" +
           "            border: 1px solid #17a2e6;\n" +
           "            color: #17a2e6;\n" +
           "            text-decoration: none;\n" +
           "            padding: 5px 20px;\n" +
           "            font-size: 14px;\n" +
           "            margin: 5px 0;\n" +
           "        }\n" +
           "    \n" +
           "        .button-blank:hover {\n" +
           "            background-color: #17a2e6;\n" +
           "            color: #FFFFFF;\n" +
           "        }\n" +
           "        \n" +
           "        .main {\n" +
           "        }\n" +
           "        \n" +
           "        .main__center {\n" +
           "            padding: 0;\n" +
           "        }\n" +
           "    \n" +
           "        .main__table {\n" +
           "            background-color: #FFFFFF;\n" +
           "            max-width: 100%;\n" +
           "            box-shadow: 0 0 30px aliceblue;\n" +
           "        }\n" +
           "    \n" +
           "        .header {\n" +
           "            background: url(\"https://ibdarb.ru/bitrix/templates/ibdarb/images/header.gif\") repeat-x #33444c;\n" +
           "            height: 100px;\n" +
           "            border-bottom: 10px solid #17a2e6;\n" +
           "        }\n" +
           "    \n" +
           "        .header a {\n" +
           "            color: #ffffff;\n" +
           "        }\n" +
           "    \n" +
           "        .header__logo img {\n" +
           "            width: 350px;\n" +
           "        }\n" +
           "    \n" +
           "        .header__text {\n" +
           "            text-align: right;\n" +
           "            color: #FFFFFF;\n" +
           "            font-weight: bold;\n" +
           "        }\n" +
           "    \n" +
           "        .content {\n" +
           "            padding-top: 40px;\n" +
           "            padding-bottom: 70px;\n" +
           "        }\n" +
           "    \n" +
           "        .content__text-td {\n" +
           "            line-height: 1.6;\n" +
           "            color: #353535;\n" +
           "        }\n" +
           "\n" +
           "        .content__text-td p {\n" +
           "            margin: 1.5rem 0;\n" +
           "        }\n" +
           "\n" +
           "        .content__table {\n" +
           "            margin-top: 40px;\n" +
           "            width: 100%;\n" +
           "            border: 1px solid #2c3a41;\n" +
           "            border-collapse: collapse;\n" +
           "        }\n" +
           "\n" +
           "        .content__table td {\n" +
           "            border: 1px solid #2c3a41;\n" +
           "        }\n" +
           "\n" +
           "        .content__table th {\n" +
           "            border: 1px solid #2c3a41;\n" +
           "        }\n" +
           "\n" +
           "        .content__table th,\n" +
           "        .content__table th a,\n" +
           "        .content__table th p {\n" +
           "            color: white;\n" +
           "            font-weight: bold;\n" +
           "            margin: 0;\n" +
           "        }\n" +
           "    \n" +
           "        .content__table tr:nth-child(2n+1) {\n" +
           "            background-color: #f9f9f9;\n" +
           "        }\n" +
           "\n" +
           "        .content__table tr.event:hover {\n" +
           "            border-left: 1px solid #17a2e6;\n" +
           "        }\n" +
           "    \n" +
           "        .content__table p {\n" +
           "            margin: 10px 0;\n" +
           "            line-height: 1.6;\n" +
           "        }\n" +
           "\n" +
           "        .price {\n" +
           "            text-align: right;\n" +
           "        }\n" +
           "    \n" +
           "        .footer {\n" +
           "            color: #333333;\n" +
           "            padding-top: 40px;\n" +
           "            padding-bottom: 40px;\n" +
           "            border-top: 1px solid #dddddd;\n" +
           "        }\n" +
           "    \n" +
           "        .footer__logo-td {\n" +
           "            width: 1%;\n" +
           "        }\n" +
           "    \n" +
           "        .footer__logo-td img {\n" +
           "            padding-right: 15px;\n" +
           "            width: 120px;\n" +
           "            height: 120px;\n" +
           "        }\n" +
           "    \n" +
           "        .footer__text {\n" +
           "            font-size: 14px;\n" +
           "        }\n" +
           "    \n" +
           "    </style>\n" +
           "</head>\n" +
           "\n" +
           "<body style=\"font-size:16px;margin: 0; padding: 0; background-color: #fafafa; color: #2d2d2d\">\n" +
           "    <table class=\"main\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
           "        <tbody>\n" +
           "            <tr>\n" +
           "                <td class=\"main__center\" align=\"center\" style=\"padding: 0;\">\n" +
           "                <!--[if mso]>\n" +
           "                    <table class=\"main__table\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color: #FFFFFF; width: 100%; box-shadow: 0 0 30px aliceblue;\">\n" +
           "                    <![endif]-->\n" +
           "                    <table class=\"main__table\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color: #FFFFFF; max-width: 100%; box-shadow: 0 0 30px aliceblue;\">\n" +
           "                        <tbody>\n" +
           "                            <tr>\n" +
           "                                <td class=\"header\" align=\"center\" style=\"background-color: #33444c; border-bottom: 10px solid #17a2e6; \">\n" +
           "                                    <table style=\"width:95%\">\n" +
           "                                        <tbody>\n" +
           "                                            <tr>\n" +
           "                                                <td class=\"header__logo\"><a href=\"https://ibdarb.ru\" style=\"color: #ffffff; text-decoration: none; margin: 0; font-size: 20px; font-weight: bold;\">Институт банковского дела<br>Ассоциации российских банков</a></td>\n" +
           "                                                <td class=\"header__text\" style=\"text-align: right; color: #FFFFFF; font-weight: normal;\"><p>Москва, ул. Щербаковская, д. 38<br>тел.: +7 (495) 234-57-99</p></td>\n" +
           "                                            </tr>\n" +
           "                                        </tbody>\n" +
           "                                    </table>\n" +
           "                                </td>\n" +
           "                            </tr>\n" +
           "\n" +
           "                            <tr>\n" +
           "                            <td class=\"content\" align=\"center\" style=\"padding-top: 40px; padding-bottom: 70px;\"> \n" +
           "                                <table cellspacing=\"0\" cellpadding=\"0\" style=\"width: 85%\">\n" +
           "                                    <tbody>\n" +
           "                                        <tr>\n" +
           "                                            <td align=\"center\">\n" +
           "                                                <table>\n" +
           "                                                    <tbody>\n" +
           "                                                        <tr>\n" +
           "                                                            <td class=\"content__text-td\" style=\"line-height: 1.6; color: #353535;\"><h3>ИМЯ.ОТЧ!</h3><p style=\"margin: 1.5rem 0;\">Если Вам актуальны предложения по подготовке специалистов кредитных организаций, обучающих кассовых работников внутри Банка, то предлагаем рассмотреть обучение на <a href=\"https://ibdarb.ru/shedule/77/event22094/\">9-10 апреля</a> по программе: <a href=\"https://ibdarb.ru/shedule/77/event22094/\">«Определение платежеспособности, в том числе подлинности, денежных знаков Банка России и подлинности банкнот иностранных государств (группы иностранных государств)».</a></p><p style=\"margin: 1.5rem 0;\">Напоминаем, что рекомендации Банка России по обучению кассовых работников кредитных организаций в целях осуществления ими контроля подлинности денежных знаков обновлены от 07.06.2019г.</p><p style=\"margin: 1.5rem 0;\">Стоимость участия в режиме вебинара - 21&nbsp;990 руб.</p><p style=\"margin: 1.5rem 0;\">Проводит представитель Банка России.</p><p style=\"margin: 1.5rem 0;\"><a href=\"https://ibdarb.ru/shedule/77/event22094/reviews/\">ОТЗЫВЫ</a> о программе <a href=\"https://ibdarb.ru/shedule/77/event22094/reviews/\">ЗДЕСЬ</a></p><div><!--[if mso]>\n" +
           "                                                <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"https://ibdarb.ru/shedule/77/event22094/order/\" style=\"height:36px;v-text-anchor:middle;width:200px;padding-bottom:20px;\" arcsize=\"8%\" strokecolor=\"#17a2e6\" fillcolor=\"#17a2e6\">\n" +
           "                                                <w:anchorlock/>\n" +
           "                                                <center style=\"color:#ffffff;font-size:18px;\">Зарегистрироваться</center>\n" +
           "                                                </v:roundrect>\n" +
           "                                                <div style=\"visibility: hidden; display: none;\">\n" +
           "                                            <![endif]-->\n" +
           "\n" +
           "                                                <a href=\"https://ibdarb.ru/shedule/77/event22094/order/\" style=\"display: inline-block; background-color: #17a2e6; border-radius: 6px; color: #FFFFFF; text-decoration: none; padding: 5px 20px; font-size: 18px; box-shadow: 0 10px 20px #bbdbeb;\">Зарегистрироваться</a>\n" +
           "                                            \n" +
           "                                            <!--[if !(mso)]>\n" +
           "                                                </div>\n" +
           "                                                <p><br></p>\n" +
           "                                            <![endif]--></div><br><p style=\"margin: 1.5rem 0;\">Дополнительно предлагаем рассмотреть: </p><table class=\"content__table\" cellspacing=\"0\" cellpadding=\"10\" style=\"margin-top: 40px; width: 100%; border: 1px solid #eaeaea; border-collapse: collapse;\">\n" +
           "                                <colgroup><col width=\"10%\">\n" +
           "                                <col width=\"60%\">\n" +
           "                                <col width=\"30%\">\n" +
           "                                </colgroup><tbody>\n" +
           "                                    <tr>\n" +
           "                                        <th class=\"content__table-header\" colspan=\"3\" style=\"text-align: left; background-color: #2c3a41; color: #ffffff; border: 1px solid #2c3a41; font-weight: bold;\">\n" +
           "                                        </th>\n" +
           "                                    </tr>\n" +
           "                                <tr class=\"event\"><td class=\"date\" style=\"border: 1px solid #2c3a41;\"><p><b>NEW<br>9<br>апреля<br></b></p></td><td class=\"description\" style=\"border: 1px solid #2c3a41;\"><p style=\"padding: 10px 0; line-height: 1.6;\"><a href=\"https://ibdarb.ru/shedule/12/event22085/\">Технологические аспекты механизма удаленной идентификации физических лиц с использованием биометрических параметров (далее БИОМЕТРИЯ) (Закон от 31.12.2017 № 482-ФЗ).</a><p><b>Проводит Наумов А.Е.</b></p></p></td><td class=\"price\" style=\"text-align: right; width: 30%; border: 1px solid #2c3a41;\"><p style=\"padding: 10px 0; line-height: 1.6;\">8990 руб.</p></td></tr><tr class=\"event\"><td class=\"date\" style=\"border: 1px solid #2c3a41;\"><p><b>NEW<br>20<br>апреля<br></b></p></td><td class=\"description\" style=\"border: 1px solid #2c3a41;\"><p style=\"padding: 10px 0; line-height: 1.6;\"><a href=\"https://ibdarb.ru/shedule/1/event22082/\">Коммуникационные навыки в моменте проверок или как «договориться» с госорганом</a><p><b>Проводит Президент Некоммерческого партнерства \"Общество защиты прав кредиторов и взыскателей\".</b></p></p></td><td class=\"price\" style=\"text-align: right; width: 30%; border: 1px solid #2c3a41;\"><p style=\"padding: 10px 0; line-height: 1.6;\">13990 руб.</p></td></tr></tbody>\n" +
           "                            </table></td>\n" +
           "                                                        </tr>\n" +
           "                                                    </tbody>\n" +
           "                                                </table>\n" +
           "                                            </td>\n" +
           "                                        </tr>\n" +
           "                                    </tbody>\n" +
           "                                </table>\n" +
           "                            </td>\n" +
           "                            </tr>\n" +
           "\n" +
           "                            <tr>\n" +
           "                                <td class=\"footer\" align=\"center\" style=\"color: #333333; padding-top: 40px; padding-bottom: 40px; border-top: 1px solid #dddddd;\">\n" +
           "                                    <table style=\"width:85%\">\n" +
           "                                        <tbody>\n" +
           "                                            <tr>\n" +
           "                                                <td class=\"footer__logo-td\" style=\"width: 1%;height:120px;\">\n" +
           "                                                    КАРТИНКА\n" +
           "                                                </td>\n" +
           "                                                <td>\n" +
           "                                                    <p class=\"footer__text\" style=\"font-size: 14px;\"><b>\n" +
           "                                                        С пожеланиями развития,<br>\n" +
           "                                                        клиентский отдел ИБД АРБ<br><br></b>\n" +
           "                                                        тел.: +7 (495) 234-57-99 (многоканальный)<br>\n" +
           "                                                        эл. почта: <a href=\"mailto:registration@ibdarb.ru\">registration@ibdarb.ru</a><br>\n" +
           "                                                        сайт: <a href=\"https://www.ibdarb.ru\">www.ibdarb.ru</a>\n" +
           "                                                    </p>\n" +
           "                                                </td>\n" +
           "                                            </tr>\n" +
           "                                        </tbody>\n" +
           "                                    </table>\n" +
           "                                </td>\n" +
           "                                <td>\n" +
           "                                    \n" +
           "                                </td>\n" +
           "                            </tr>\n" +
           "                        </tbody>\n" +
           "                    </table>\n" +
           "                </td>\n" +
           "            </tr>\n" +
           "        </tbody>\n" +
           "    </table>\n" +
           "\n" +
           "\n" +
           "</body></html>";


}
