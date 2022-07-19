package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

/*
 * Complete the 'timeInWords' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. INTEGER h
 *  2. INTEGER m
 */

var NumberToWordMap = map[int32]string{
      1:  "one",
      2:  "two",
      3:  "three",
      4:  "four",
      5:  "five",
      6:  "six",
      7:  "seven",
      8:  "eight",
      9:  "nine",
      10: "ten",
      11: "eleven",
      12: "twelve",
      13: "thirteen",
      14: "fourteen",
      16: "sixteen",
      17: "seventeen",
      18: "eighteen",
      19: "nineteen",
      20: "twenty",
}

func numberToWord(num int32) string {
    if num < 20 {
        return NumberToWordMap[num]
    }

    var remainder = num % 10
    if remainder == 0 {
        return NumberToWordMap[num]
    } else {
        var word = NumberToWordMap[num - remainder] + " " + NumberToWordMap[remainder]
        return word
    }
}

func timeInWords(h int32, m int32) string {
    var minutesToNextHr = 60 - m
    var preposition string
    if minutesToNextHr < 30 {
        m = minutesToNextHr
        h++
        preposition = "to"
    } else {
        preposition = "past"
    }

    var timeInWords string
    switch {
        case m == 0:
            timeInWords = fmt.Sprintf("%s o' clock", numberToWord(h))
        case m == 1:
            timeInWords = fmt.Sprintf("%s minute %s %s", numberToWord(m), preposition, numberToWord(h))
        case m == 15:
            timeInWords = fmt.Sprintf("quarter %s %s", preposition, numberToWord(h))
        case m == 30:
            timeInWords = fmt.Sprintf("half past %s", numberToWord(h))
        default:
            timeInWords = fmt.Sprintf("%s minutes %s %s", numberToWord(m), preposition, numberToWord(h))
    }
    return timeInWords
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    hTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    h := int32(hTemp)

    mTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    m := int32(mTemp)

    result := timeInWords(h, m)

    fmt.Fprintf(writer, "%s\n", result)

    writer.Flush()
}

func readLine(reader *bufio.Reader) string {
    str, _, err := reader.ReadLine()
    if err == io.EOF {
        return ""
    }

    return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
    if err != nil {
        panic(err)
    }
}
