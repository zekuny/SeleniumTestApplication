Option Explicit
On Error Resume Next
ExcelMacroExample
Sub ExcelMacroExample()
Dim xlApp
Dim xlBook
Set xlApp = CreateObject("Excel.Application")
Set xlBook = xlApp.Workbooks.Open("/Users/zekuny/Desktop/BIReport/1/5/2015-11-2-15-3-321/5/Report/5.xls", 0, False)
xlApp.Run "Sheet1.CommandButton1_Click"
xlBook.Save
xlApp.Quit
Set xlBook = Nothing
Set xlApp = Nothing
End Sub