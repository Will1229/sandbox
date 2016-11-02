import unittest

import main


class Test(unittest.TestCase):
    def test_test1(self):
        self.assertEqual('', main.test1(''))
        self.assertEqual('a3b2c1d4e2f1a1', main.test1('aaabbcddddeefa'))
        self.assertEqual('a3b2c1d4e2', main.test1('aaabbcddddee'))
        self.assertEqual('b3', main.test1('bbb'))
