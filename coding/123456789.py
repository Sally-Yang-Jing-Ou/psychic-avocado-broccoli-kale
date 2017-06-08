def combinations(first, i, targetSum):
    if i == 100:
        if eval(first) == targetSum:
            print(first)
        return
     if i != 1:
    	combinations(first + "+" + str(i), i + 1, targetSum)
    combinations(first + "-" + str(i), i + 1, targetSum)
    combinations(first + str(i), i + 1, targetSum)

combinations("", 1, 50)



# 
# Your previous Python 2 content is preserved below:
# 
# def say_hello():
#     print 'Hello, World'
# 
# for i in xrange(5):
#     say_hello()
# 
# 
# # 
# # Your previous Python 3 content is preserved below:
# # 
# # def combinations(first, i, targetSum):
# #     if i == 100:
# #         if eval(first) == targetSum:
# #             print(first)
# #         return
# #     combinations(first + "+" + str(i), i + 1, targetSum)
# #     combinations(first + "-" + str(i), i + 1, targetSum)
# #     combinations(first + str(i), i + 1, targetSum)
# # 
# 
