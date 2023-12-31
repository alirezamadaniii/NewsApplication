package com.example.global.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomDateUtilities {

    public static String getCurrentShamsidate() {
        Locale loc = new Locale("en_US");
        SolarCalendar sc = new SolarCalendar();
        return sc.year + "/" + String.format(loc, "%02d",
                sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }

    public static String getCustomShamsidate(Long timestamp) {
        Locale loc = new Locale("en_US");
        SolarCalendar sc = new SolarCalendar(timestamp);
        return sc.year + "/" + String.format(loc, "%02d",
                sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }

    public static Integer getCurrentShamsiYear() {
        SolarCalendar sc = new SolarCalendar();
        return sc.year;
    }

    private static class SolarCalendar {

        public String strWeekDay = "";
        public String strMonth = "";

        int date;
        int month;
        int year;

        public SolarCalendar() {
            Date MiladiDate = new Date();
            calcSolarCalendar(MiladiDate);
        }

        public SolarCalendar(Long timeStamp) {
            Date MiladiDate = new Date(timeStamp);
            calcSolarCalendar2(MiladiDate);
        }

        public SolarCalendar(Date MiladiDate) {
            calcSolarCalendar(MiladiDate);
        }

        private void calcSolarCalendar(Date MiladiDate) {

            int ld;
//            int miladiYear = MiladiDate.getYear() + 1900;
//            int miladiMonth = MiladiDate.getMonth() + 1;
//            int miladiDate = MiladiDate.getDate();
//            int WeekDay = MiladiDate.getDay();

            int miladiYear = Calendar.getInstance().get(Calendar.YEAR);
            int miladiMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int miladiDate = Calendar.getInstance().get(Calendar.DATE);
            int WeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            int[] buf1 = new int[12];
            int[] buf2 = new int[12];

            buf1[0] = 0;
            buf1[1] = 31;
            buf1[2] = 59;
            buf1[3] = 90;
            buf1[4] = 120;
            buf1[5] = 151;
            buf1[6] = 181;
            buf1[7] = 212;
            buf1[8] = 243;
            buf1[9] = 273;
            buf1[10] = 304;
            buf1[11] = 334;

            buf2[0] = 0;
            buf2[1] = 31;
            buf2[2] = 60;
            buf2[3] = 91;
            buf2[4] = 121;
            buf2[5] = 152;
            buf2[6] = 182;
            buf2[7] = 213;
            buf2[8] = 244;
            buf2[9] = 274;
            buf2[10] = 305;
            buf2[11] = 335;

            if ((miladiYear % 4) != 0) {
                date = buf1[miladiMonth - 1] + miladiDate;

                if (date > 79) {
                    date = date - 79;
                    if (date <= 186) {
                        if (date % 31 == 0) {
                            month = date / 31;
                            date = 31;
                        } else {
                            month = (date / 31) + 1;
                            date = (date % 31);
                        }
                    } else {
                        date = date - 186;

                        if (date % 30 == 0) {
                            month = (date / 30) + 6;
                            date = 30;
                        } else {
                            month = (date / 30) + 7;
                            date = (date % 30);
                        }
                    }
                    year = miladiYear - 621;
                } else {
                    if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                        ld = 11;
                    } else {
                        ld = 10;
                    }
                    date = date + ld;

                    if (date % 30 == 0) {
                        month = (date / 30) + 9;
                        date = 30;
                    } else {
                        month = (date / 30) + 10;
                        date = (date % 30);
                    }
                    year = miladiYear - 622;
                }
            } else {
                date = buf2[miladiMonth - 1] + miladiDate;

                if (miladiYear >= 1996) {
                    ld = 79;
                } else {
                    ld = 80;
                }
                if (date > ld) {
                    date = date - ld;

                    if (date <= 186) {
                        if (date % 31 == 0) {
                            month = (date / 31);
                            date = 31;
                        } else {
                            month = (date / 31) + 1;
                            date = (date % 31);
                        }
                    } else {
                        date = date - 186;

                        if (date % 30 == 0) {
                            month = (date / 30) + 6;
                            date = 30;
                        } else {
                            month = (date / 30) + 7;
                            date = (date % 30);
                        }
                    }
                    year = miladiYear - 621;
                } else {
                    date = date + 10;

                    if (date % 30 == 0) {
                        month = (date / 30) + 9;
                        date = 30;
                    } else {
                        month = (date / 30) + 10;
                        date = (date % 30);
                    }
                    year = miladiYear - 622;
                }

            }

            switch (month) {
                case 1:
                    strMonth = "فروردين";
                    break;
                case 2:
                    strMonth = "ارديبهشت";
                    break;
                case 3:
                    strMonth = "خرداد";
                    break;
                case 4:
                    strMonth = "تير";
                    break;
                case 5:
                    strMonth = "مرداد";
                    break;
                case 6:
                    strMonth = "شهريور";
                    break;
                case 7:
                    strMonth = "مهر";
                    break;
                case 8:
                    strMonth = "آبان";
                    break;
                case 9:
                    strMonth = "آذر";
                    break;
                case 10:
                    strMonth = "دي";
                    break;
                case 11:
                    strMonth = "بهمن";
                    break;
                case 12:
                    strMonth = "اسفند";
                    break;
            }

            switch (WeekDay) {

                case 0:
                    strWeekDay = "يکشنبه";
                    break;
                case 1:
                    strWeekDay = "دوشنبه";
                    break;
                case 2:
                    strWeekDay = "سه شنبه";
                    break;
                case 3:
                    strWeekDay = "چهارشنبه";
                    break;
                case 4:
                    strWeekDay = "پنج شنبه";
                    break;
                case 5:
                    strWeekDay = "جمعه";
                    break;
                case 6:
                    strWeekDay = "شنبه";
                    break;
            }

        }


        private void calcSolarCalendar2(Date MiladiDate) {

            int ld;
            int miladiYear = MiladiDate.getYear() + 1900;
            int miladiMonth = MiladiDate.getMonth() + 1;
            int miladiDate = MiladiDate.getDate();
            int WeekDay = MiladiDate.getDay();

//            int miladiYear = Calendar.getInstance().get(Calendar.YEAR);
//            int miladiMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
//            int miladiDate = Calendar.getInstance().get(Calendar.DATE);
//            int WeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            int[] buf1 = new int[12];
            int[] buf2 = new int[12];

            buf1[0] = 0;
            buf1[1] = 31;
            buf1[2] = 59;
            buf1[3] = 90;
            buf1[4] = 120;
            buf1[5] = 151;
            buf1[6] = 181;
            buf1[7] = 212;
            buf1[8] = 243;
            buf1[9] = 273;
            buf1[10] = 304;
            buf1[11] = 334;

            buf2[0] = 0;
            buf2[1] = 31;
            buf2[2] = 60;
            buf2[3] = 91;
            buf2[4] = 121;
            buf2[5] = 152;
            buf2[6] = 182;
            buf2[7] = 213;
            buf2[8] = 244;
            buf2[9] = 274;
            buf2[10] = 305;
            buf2[11] = 335;

            if ((miladiYear % 4) != 0) {
                date = buf1[miladiMonth - 1] + miladiDate;

                if (date > 79) {
                    date = date - 79;
                    if (date <= 186) {
                        if (date % 31 == 0) {
                            month = date / 31;
                            date = 31;
                        } else {
                            month = (date / 31) + 1;
                            date = (date % 31);
                        }
                    } else {
                        date = date - 186;

                        if (date % 30 == 0) {
                            month = (date / 30) + 6;
                            date = 30;
                        } else {
                            month = (date / 30) + 7;
                            date = (date % 30);
                        }
                    }
                    year = miladiYear - 621;
                } else {
                    if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                        ld = 11;
                    } else {
                        ld = 10;
                    }
                    date = date + ld;

                    if (date % 30 == 0) {
                        month = (date / 30) + 9;
                        date = 30;
                    } else {
                        month = (date / 30) + 10;
                        date = (date % 30);
                    }
                    year = miladiYear - 622;
                }
            } else {
                date = buf2[miladiMonth - 1] + miladiDate;

                if (miladiYear >= 1996) {
                    ld = 79;
                } else {
                    ld = 80;
                }
                if (date > ld) {
                    date = date - ld;

                    if (date <= 186) {
                        if (date % 31 == 0) {
                            month = (date / 31);
                            date = 31;
                        } else {
                            month = (date / 31) + 1;
                            date = (date % 31);
                        }
                    } else {
                        date = date - 186;

                        if (date % 30 == 0) {
                            month = (date / 30) + 6;
                            date = 30;
                        } else {
                            month = (date / 30) + 7;
                            date = (date % 30);
                        }
                    }
                    year = miladiYear - 621;
                } else {
                    date = date + 10;

                    if (date % 30 == 0) {
                        month = (date / 30) + 9;
                        date = 30;
                    } else {
                        month = (date / 30) + 10;
                        date = (date % 30);
                    }
                    year = miladiYear - 622;
                }

            }

            switch (month) {
                case 1:
                    strMonth = "فروردين";
                    break;
                case 2:
                    strMonth = "ارديبهشت";
                    break;
                case 3:
                    strMonth = "خرداد";
                    break;
                case 4:
                    strMonth = "تير";
                    break;
                case 5:
                    strMonth = "مرداد";
                    break;
                case 6:
                    strMonth = "شهريور";
                    break;
                case 7:
                    strMonth = "مهر";
                    break;
                case 8:
                    strMonth = "آبان";
                    break;
                case 9:
                    strMonth = "آذر";
                    break;
                case 10:
                    strMonth = "دي";
                    break;
                case 11:
                    strMonth = "بهمن";
                    break;
                case 12:
                    strMonth = "اسفند";
                    break;
            }

            switch (WeekDay) {

                case 0:
                    strWeekDay = "يکشنبه";
                    break;
                case 1:
                    strWeekDay = "دوشنبه";
                    break;
                case 2:
                    strWeekDay = "سه شنبه";
                    break;
                case 3:
                    strWeekDay = "چهارشنبه";
                    break;
                case 4:
                    strWeekDay = "پنج شنبه";
                    break;
                case 5:
                    strWeekDay = "جمعه";
                    break;
                case 6:
                    strWeekDay = "شنبه";
                    break;
            }
        }
    }

}


